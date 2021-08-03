package com.magneto.entrypoint.service.impl;

import com.magneto.crosscutting.constants.Constants;
import com.magneto.crosscutting.exception.BusinessException;
import com.magneto.dataprovider.jpa.entity.DNA;
import com.magneto.dataprovider.jpa.repository.DNARepository;
import com.magneto.dto.DNARq;
import com.magneto.dto.StatsRs;
import com.magneto.entrypoint.service.MagnetoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class MagnetoServiceImpl implements MagnetoService {

    private final DNARepository dnaRepository;

    @Override
    public Mono<Boolean> verifyDna(final DNARq dnaRq) {
        String[] dna = dnaRq.getDna();
        boolean isMutant = isMutant(dna);
        return saveDna(dna, isMutant);
    }

    @Override
    public Mono<StatsRs> getStats() {
        return dnaRepository.getAllByDnaMutant(Constants.IS_MUTANT).count()
                .flatMap(count ->
                        Mono.just(count)
                                .zipWith(dnaRepository.getAllByDnaMutant(Constants.IS_HUMAN).count())
                                .map(t -> StatsRs.builder().countMutantDna(t.getT1())
                                        .countHumanDna(t.getT2())
                                        .ratio(BigDecimal.valueOf(t.getT1()).divide(BigDecimal.valueOf(t.getT2()), Constants.RATIO_SCALE, RoundingMode.CEILING).doubleValue())
                                        .build())
                ).doOnError(exception -> log.error("Fail to getStats ", exception.getMessage()));
    }

    private boolean isMutant(String[] dna) {
        List<char[]> dnaMatrix = new ArrayList<>();
        for (String nitrogenBase : dna) {
            char[] nitrogenBaseChar = nitrogenBase.toCharArray();
            for (char c : nitrogenBaseChar) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    throw new BusinessException(Constants.CHARACTERS_ERROR_MESSAGE);
                }
            }
            dnaMatrix.add(nitrogenBase.toCharArray());
        }
        for (int i = 0; i < dnaMatrix.size(); i++) {
            if (dnaMatrix.size() != dnaMatrix.get(i).length) {
                throw new BusinessException(Constants.MATRIX_ERROR_MESSAGE);
            }
        }
        int nitrogenBaseMutantCount = isMutant(dna, 0);
        if (nitrogenBaseMutantCount == Constants.NITROGEN_BASE_MUTANT_COUNT) {
            return true;
        }
        dna = verticalOrderingDna(dnaMatrix);
        nitrogenBaseMutantCount = isMutant(dna, nitrogenBaseMutantCount);
        if (nitrogenBaseMutantCount == Constants.NITROGEN_BASE_MUTANT_COUNT) {
            return true;
        }
        dna = diagonalOrderingDna(dnaMatrix);
        nitrogenBaseMutantCount = isMutant(dna, nitrogenBaseMutantCount);
        if (nitrogenBaseMutantCount == Constants.NITROGEN_BASE_MUTANT_COUNT) {
            return true;
        }
        dna = reverseDiagonalOrderingDna(dnaMatrix);
        nitrogenBaseMutantCount = isMutant(dna, nitrogenBaseMutantCount);
        if (nitrogenBaseMutantCount == Constants.NITROGEN_BASE_MUTANT_COUNT) {
            return true;
        }
        return false;
    }

    private int isMutant(String[] dna, int nitrogenBaseMutantCount) {
        for (String nitrogenBase : dna) {
            char[] nitrogenBaseChar = nitrogenBase.toCharArray();
            boolean isMutant = analyzeNitrogenBase(nitrogenBaseChar);
            if (isMutant) {
                nitrogenBaseMutantCount = nitrogenBaseMutantCount + 1;
                if (nitrogenBaseMutantCount == Constants.NITROGEN_BASE_MUTANT_COUNT) {
                    return nitrogenBaseMutantCount;
                }
            }
        }
        return nitrogenBaseMutantCount;
    }

    private boolean analyzeNitrogenBase(char[] nitrogenBaseChar) {
        boolean isNitrogenBaseMutant = false;
        if (nitrogenBaseChar.length >= 4) {
            int count = 0;
            for (int i = 0; i < nitrogenBaseChar.length; i++) {
                if (Constants.NITROGEN_BASE_MUTANT_CHARACTER_NUMBER <= nitrogenBaseChar.length - i || isNitrogenBaseMutant && (i + 1) < nitrogenBaseChar.length) {
                    isNitrogenBaseMutant = nitrogenBaseChar[i] == nitrogenBaseChar[i + 1];
                    count = isNitrogenBaseMutant ? count + 1 : 0;
                    if ((Constants.NITROGEN_BASE_MUTANT_CHARACTER_NUMBER - 1) == count) {
                        return true;
                    }
                }
            }
        }
        return isNitrogenBaseMutant;
    }

    private String[] verticalOrderingDna(List<char[]> dnaMatrix) {
        String[] dna = new String[dnaMatrix.size()];
        for (int j = 0; j < dnaMatrix.size(); j++) {
            String nitrogenBase = "";
            for (int i = 0; i < dnaMatrix.size(); i++) {
                nitrogenBase = nitrogenBase.concat(String.valueOf(dnaMatrix.get(i)[j]));
            }
            dna[j] = nitrogenBase;
        }
        return dna;
    }

    private String[] diagonalOrderingDna(List<char[]> dnaMatrix) {
        List<String> dna = new ArrayList<>();
        int i = dnaMatrix.size();
        for (int d = 1 - i, j = 0; d < i; d++) {
            j = (d > 0) ? j + 1 : j;
            String nitrogenBase = "";
            for (int v = j, h = -Math.min(0, d); v < i && h < i; v++, h++) {
                nitrogenBase = nitrogenBase.concat(String.valueOf(dnaMatrix.get(v)[h]));
            }
            if (Constants.NITROGEN_BASE_MUTANT_CHARACTER_NUMBER <= nitrogenBase.length()) {
                dna.add(nitrogenBase);
            }
        }
        return dna.toArray(new String[0]);
    }

    private String[] reverseDiagonalOrderingDna(List<char[]> dnaMatrix) {
        List<String> dna = new ArrayList<>();
        int i = dnaMatrix.size();
        for (int d = i - 1, j = i - 1; d > -i; d--) {
            j = (d < 0) ? j - 1 : j;
            String nitrogenBase = "";
            for (int v = j, h = Math.max(0, d); v >= 0 && h < i; v--, h++) {
                nitrogenBase = nitrogenBase.concat(String.valueOf(dnaMatrix.get(v)[h]));
            }
            if (4 <= nitrogenBase.length()) {
                dna.add(nitrogenBase);
            }
        }
        return dna.toArray(new String[0]);
    }

    @Transactional
    Mono<Boolean> saveDna(final String[] dna, boolean isMutant) {
        return dnaRepository.save(DNA.builder().dnaValue(Arrays.toString(dna)).dnaMutant(isMutant ? Constants.IS_MUTANT : Constants.IS_HUMAN).build())
                .map(dnaResult -> dnaResult.getDnaMutant() == Constants.IS_MUTANT)
                .doOnError(exception -> log.error("Fail to save DNA ", exception.getMessage()));
    }
}

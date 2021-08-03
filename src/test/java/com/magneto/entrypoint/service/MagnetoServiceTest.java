package com.magneto.entrypoint.service;

import com.magneto.builder.BuilderConfigurationTest;
import com.magneto.builder.DataTestBuilder;
import com.magneto.crosscutting.exception.BusinessException;
import com.magneto.dataprovider.jpa.entity.DNA;
import com.magneto.dataprovider.jpa.repository.DNARepository;
import com.magneto.dto.StatsRs;
import com.magneto.entrypoint.service.impl.MagnetoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MagnetoServiceTest extends BuilderConfigurationTest {

    private DNARepository dnaRepository;

    private MagnetoServiceImpl magnetoService;

    @BeforeEach
    void setUp() {
        this.dnaRepository = Mockito.mock(DNARepository.class);
        this.magnetoService = new MagnetoServiceImpl(this.dnaRepository);
    }

    @Test
    void verifyMutantDnaHorizontalScopeIsDone() {
        Mockito.when(this.dnaRepository.save(Mockito.any(DNA.class)))
                .thenReturn(Mono.just(DataTestBuilder.getDNA()));
        final Mono<Boolean> dataResponse = this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.MUTANT_DNA_HORIZONTAL_SCOPE));
        Assertions.assertNotNull(dataResponse.block());
    }

    @Test
    void verifyMutantDnaVerticalScopeIsDone() {
        Mockito.when(this.dnaRepository.save(Mockito.any(DNA.class)))
                .thenReturn(Mono.just(DataTestBuilder.getDNA()));
        final Mono<Boolean> dataResponse = this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.MUTANT_DNA_VERTICAL_SCOPE));
        Assertions.assertNotNull(dataResponse.block());
    }

    @Test
    void verifyMutantDnaDiagonalScopeIsDone() {
        Mockito.when(this.dnaRepository.save(Mockito.any(DNA.class)))
                .thenReturn(Mono.just(DataTestBuilder.getDNA()));
        final Mono<Boolean> dataResponse = this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.MUTANT_DNA_DIAGONAL_SCOPE));
        Assertions.assertNotNull(dataResponse.block());
    }

    @Test
    void verifyMutantDnaReverseDiagonalScopeIsDone() {
        Mockito.when(this.dnaRepository.save(Mockito.any(DNA.class)))
                .thenReturn(Mono.just(DataTestBuilder.getDNA()));
        final Mono<Boolean> dataResponse = this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.MUTANT_DNA_REVERSE_DIAGONAL_SCOPE));
        Assertions.assertNotNull(dataResponse.block());
    }

    @Test
    void verifyHumanDnaIsDone() {
        Mockito.when(this.dnaRepository.save(Mockito.any(DNA.class)))
                .thenReturn(Mono.just(DataTestBuilder.getDNA()));
        final Mono<Boolean> dataResponse = this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.HUMAN_DNA));
        Assertions.assertNotNull(dataResponse.block());
    }

    @Test
    void verifyDnaIsThrowByCharacters() {
        Assertions.assertThrows(BusinessException.class, () ->
                this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.DNA_BAD_REQUEST_CHARACTERS)));
    }

    @Test
    void verifyDnaIsThrowByMatrix() {
        Assertions.assertThrows(BusinessException.class, () ->
                this.magnetoService.verifyDna(DataTestBuilder.getDNARq(DataTestBuilder.DNA_BAD_REQUEST_MATRIX)));
    }


    @Test
    void getStatsIsDone() {
        Mockito.when(this.dnaRepository.getAllByDnaMutant(Mockito.anyInt()))
                .thenReturn(Flux.just(DataTestBuilder.getDNA()));
        final Mono<StatsRs> dataResponse = this.magnetoService.getStats();
        Assertions.assertNotNull(dataResponse.block());
    }

}

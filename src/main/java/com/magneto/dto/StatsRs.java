package com.magneto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatsRs {

    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;
}

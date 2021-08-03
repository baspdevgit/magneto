package com.magneto.entrypoint.service;

import com.magneto.dto.DNARq;
import com.magneto.dto.StatsRs;
import reactor.core.publisher.Mono;

public interface MagnetoService {

    Mono<Boolean> verifyDna(final DNARq dnaRq);

    Mono<StatsRs> getStats();
}

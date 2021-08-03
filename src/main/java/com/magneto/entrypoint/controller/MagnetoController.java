package com.magneto.entrypoint.controller;

import com.magneto.crosscutting.constants.ResourceEndpoint;
import com.magneto.dto.DNARq;
import com.magneto.dto.StatsRs;
import com.magneto.entrypoint.service.MagnetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MagnetoController {

    private final MagnetoService magnetoService;

    @PostMapping(value = ResourceEndpoint.MUTANT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Boolean>> verifyDna(@Valid @RequestBody DNARq dnaRq) {
        return magnetoService.verifyDna(dnaRq).map(isMutant ->
                isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        );
    }

    @GetMapping(value = ResourceEndpoint.STATS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<StatsRs>> getStats() {
        return magnetoService.getStats().map(ResponseEntity::ok);
    }
}

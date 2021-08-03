package com.magneto.dataprovider.jpa.repository;


import com.magneto.dataprovider.jpa.entity.DNA;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DNARepository extends ReactiveCrudRepository<DNA, Long> {

    Flux<DNA> getAllByDnaMutant(int mutant);

}

package com.magneto.dataprovider.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@Table("DNA")
public class DNA {

    @Id
    @Column("DNA_ID")
    private Long dnaId;

    @Column("DNA_VALUE")
    private String dnaValue;

    @Column("DNA_MUTANT")
    private int dnaMutant;
}

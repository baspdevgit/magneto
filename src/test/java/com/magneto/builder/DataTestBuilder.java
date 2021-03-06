package com.magneto.builder;

import com.google.gson.Gson;
import com.magneto.crosscutting.constants.Constants;
import com.magneto.dataprovider.jpa.entity.DNA;
import com.magneto.dto.DNARq;
import com.magneto.dto.StatsRs;

public class DataTestBuilder {

    public static String[] DNA_BAD_REQUEST_CHARACTERS = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTB"};

    public static String[] DNA_BAD_REQUEST_MATRIX = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTTT"};

    public static String[] MUTANT_DNA_HORIZONTAL_SCOPE = new String[]{
            "CCCCGAATGTCCCTCGCACT", "CCTTGCAACCTGTAATCCAG", "CTCCGTGTAGACCCTCAGCA", "CTCCCGATATCAGCTTCCCA",
            "CAGGATGATCGTACGGCATG", "ACCTCGAAGTACTTATCTAG", "CTAGCCCTGTGAGGAGTGGG", "GGAGTGCTGGTTGACCGACG",
            "GGTCCGTACAGTATGTTAAG", "CCCACCGATTCCCTCCTGAG", "GGTTCTACTCACGCGTCCGT", "ACGATTGTGACAGGCGTACG",
            "CTACGCGCCTCCGATAGCTA", "GTGAATTGCACTACTGAATG", "GCTAAAACACTTGTCCCTGT", "CACTTATCCAGGAGAGCTCG",
            "GGACTGGACTTATAAGTTTA", "ATGTGTTAAATGTGCCGCTG", "GACGTAGATGCGGTTGACGC", "GCTATGTCCGTGCGCACTAT"};

    public static String[] MUTANT_DNA_VERTICAL_SCOPE = new String[]{
            "CTGTGAATGTCCCTCGCACT", "CCTTGCAACCTGTAATCCAG", "CTCCGTGTAGACCCTCAGCA", "CTCCCGATATCAGCTTCCCA",
            "CAGGATGATCGTACGGCATG", "ACCTCGAAGTACTTATCTAG", "CTAGCCCTGTGAGGAGTGGG", "GGAGTGCTGGTTGACCGACG",
            "GGTCCGTACAGTATGTTAAG", "CCCACCGATTCCCTCCTGAG", "GGTTCTACTCACGCGTCCGT", "ACGATTGTGACAGGCGTACG",
            "CTACGCGCCTCCGATAGCTA", "GTGAATTGCACTACTGAATG", "GCTACGACACTTGTCCCTGT", "CACTTATCCAGGAGAGCTCG",
            "GGACTGGACTTATAAGTTTA", "ATGTGTTAAATGTGCCGCTG", "GACGTAGATGCGGTTGACGC", "GCTATGTCCGTGCGCACTAT"};

    public static String[] MUTANT_DNA_DIAGONAL_SCOPE = new String[]{
            "CTGTGAATGTCCCTCGCACT", "CCTTGCAACCTGTAATCCAG", "TTCCGTGTAGACCCTCAGCA", "CTCCCGATATCAGCTTCCCA",
            "CAGGATGATCGTACGGCATG", "ACCTCGAAGTACTTATCTAT", "CTAGCCCTGTGAGGAGTGGG", "GGAGTGCTGGTTGACCGACC",
            "GGTCCGTACAGTATGTTAAG", "CCCACCGATTCCCTCCTGAG", "GGTTCTACTCACGCGTCCGT", "ACGATTGTGACAGGCGTACG",
            "CTACGCGCCTCCGATAGCTA", "GTGAATTGCACTACTGAATG", "GCTACGACACTTGTCCCTGT", "CACTTATCCAGGAGAGCTCG",
            "GGACTGGACTTATAAGTTTA", "ATGTGTTAAATGTGCCGCTG", "GACGTAGATGCGGTTGACGC", "GCTATGTCCGTGCGCACTAT"};

    public static String[] MUTANT_DNA_REVERSE_DIAGONAL_SCOPE = new String[]{
            "CTGTGAATGTCCCTCGCACT", "CATTGCAACCTGTAATCCAG", "TTACGTGTAGACCCTCAGCA", "CTCTCGATATCAGCTTCCCA",
            "CAGGATGATCGTACGGCATG", "ACCTCGAAGTACTTATCTAT", "CTAGCCCTGTGAGGAGTGGG", "GGAGTGCTGGTTGACCGACC",
            "GGTCCGTACAGTATGTTAAG", "CCCACCGATTCCCTCCTGAG", "GGTTCTACTCACGCGTCCGT", "ACGATTGTGACAGGCGTACG",
            "CTACGCGCCTCCGATAGCTA", "GTGAATTGCACTACTGAATG", "GCTACGACACTTGTCCCTGT", "CACTTATCCAGGAGAGCTCG",
            "GGACTGGACTTATAAGTTTA", "ATGTGTTAAATGTGCCGCTG", "GACGTAGATGCGGTTGACGC", "GCTATGTCCGTGCGCACTAT"};

    public static String[] HUMAN_DNA = new String[]{
            "CTGTGAATGTCCCTCGCACT", "CATTGCAACCTGTAATCCAG", "TTACGTGTAGACCCTCAGCA", "CTCTCGATATCAGCTTCCCA",
            "CAGGATGATCGTACGGCATG", "ACCTCGAAGTACTTATCTAT", "CTAGCCCTGTGAGGAGTGGG", "GGAGTGCTGGTTGACCGACC",
            "GGTCCGTACAGTATGTTAAG", "CCCACCGATTCCCTCCTGAG", "GGTTCTACTCACGCGTCCGT", "ACGATTGTGACAGGCGTACG",
            "CTACGCGCCTCCGATAGCTA", "GTGAATTGCACTACTGAATG", "GCGACGACACTTGTCCCTGT", "CACTTATCCAGGAGAGCTCG",
            "GGACTGGACTTATAAGTTTA", "ATGTGTTAAATGTGCCGCTG", "GACGTAGATGCGGTTGACGC", "GCTATGTCCGTGCGCACTAT"};


    public static DNA getDNA() {
        return DNA.builder()
                .dnaId(1L)
                .dnaMutant(Constants.IS_MUTANT)
                .dnaValue("[ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .build();
    }

    public static StatsRs getStats() {
        return StatsRs.builder()
                .ratio(0.4)
                .countHumanDna(100L)
                .countMutantDna(40L)
                .build();
    }

    public static DNARq getDNARq(String[] dna) {
        return DNARq.builder()
                .dna(dna)
                .build();
    }

    public static String dnaRqAsJsonString() {
        return new Gson().toJson(getDNARq(DataTestBuilder.MUTANT_DNA_HORIZONTAL_SCOPE));
    }
}

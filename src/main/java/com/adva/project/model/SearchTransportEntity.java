package com.adva.project.model;

import lombok.Data;

import java.util.Set;

@Data
public class SearchTransportEntity {

    private Set<TypeOfTransport> searchedTypes;
    private int minPrice;
    private int maxPrice;
    private double minMileage;
    private double maxMileage;
}
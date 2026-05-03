package org.example.web.master.industry.domain;

import java.math.BigDecimal;

public class IndustryResponseDto {
    private final Integer id;
    private final String name;
    private final String sectorName;
    private final String description;
    private final BigDecimal avgPer;

    public IndustryResponseDto(Integer id, String name, String sectorName, String description, BigDecimal avgPer) {
        this.id = id;
        this.name = name;
        this.sectorName = sectorName;
        this.description = description;
        this.avgPer = avgPer;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSectorName() {
        return sectorName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAvgPer() {
        return avgPer;
    }
}

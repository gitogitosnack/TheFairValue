package org.example.web.stock.industry.domain;

import java.math.BigDecimal;

public record IndustryForm(
                Integer id,
                String name,
                String sectorName,
                String description,
                BigDecimal avgPer) {
}

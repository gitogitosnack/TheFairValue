package org.example.web.stock.stockDetail.domain;

public class StockAnalysisResponse {

    // temporary dto
    private KeyFinancialIndicatorDto keyFinancialIndicatorDto;
    private FinancialIndicatorDto financialIndicatorDto;

    // Getter and Setter
    public KeyFinancialIndicatorDto getKeyFinancialIndicatorDto() {
        return keyFinancialIndicatorDto;
    }

    public void setKeyFinancialIndicatorDto(KeyFinancialIndicatorDto keyFinancialIndicatorDto) {
        this.keyFinancialIndicatorDto = keyFinancialIndicatorDto;
    }

    public FinancialIndicatorDto getDto2() {
        return financialIndicatorDto;
    }

    public void setFinancialIndicatorDto(FinancialIndicatorDto financialIndicatorDto) {
        this.financialIndicatorDto = financialIndicatorDto;
    }
}

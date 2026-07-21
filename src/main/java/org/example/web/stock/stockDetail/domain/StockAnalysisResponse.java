package org.example.web.stock.stockDetail.domain;

public class StockAnalysisResponse {

    private TheoreticalPriceValuationDto theoreticalPriceValuationDto;
    private KeyFinancialIndicatorDto keyFinancialIndicatorDto;
    private FinancialIndicatorDto financialIndicatorDto;

    // Getter and Setter
    public TheoreticalPriceValuationDto getTheoreticalPriceValuationDto() {
        return theoreticalPriceValuationDto;
    }

    public void setTheoreticalPriceValuationDto(TheoreticalPriceValuationDto theoreticalPriceValuationDto) {
        this.theoreticalPriceValuationDto = theoreticalPriceValuationDto;
    }

    public KeyFinancialIndicatorDto getKeyFinancialIndicatorDto() {
        return keyFinancialIndicatorDto;
    }

    public void setKeyFinancialIndicatorDto(KeyFinancialIndicatorDto keyFinancialIndicatorDto) {
        this.keyFinancialIndicatorDto = keyFinancialIndicatorDto;
    }

    public FinancialIndicatorDto getFinancialIndicatorDto() {
        return financialIndicatorDto;
    }

    public void setFinancialIndicatorDto(FinancialIndicatorDto financialIndicatorDto) {
        this.financialIndicatorDto = financialIndicatorDto;
    }
}

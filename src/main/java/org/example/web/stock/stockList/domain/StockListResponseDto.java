package org.example.web.stock.stockList.domain;

public class StockListResponseDto {

    // Field
    private final String code;
    private final String stock_name;
    private final String market;

    // Constructor
    public StockListResponseDto(
            String code
            ,String stock_name
            ,String market
    ) {
        this.code = code;
        this.stock_name = stock_name;
        this.market = market;
    }

    // Getter
    public String getCode() {
        return code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getMarket() {
        return market;
    }

}

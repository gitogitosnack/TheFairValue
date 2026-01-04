package org.example.web.stock.stockList.domain;

public class StockListResponseDto {

    // Field
    private final Integer id;
    private final String ticker_symbol;
    private final String name;
    private final String market_name;

    // Constructor
    public StockListResponseDto(
            Integer id
            ,String ticker_symbol
            ,String name
            ,String market_name
    ) {
        this.id = id;
        this.ticker_symbol = ticker_symbol;
        this.name = name;
        this.market_name = market_name;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public String getName() {
        return name;
    }

    public String getMarket_name() {
        return market_name;
    }
}

package org.example.web.stock.stockList.domain;

public class StockListResponseDto {

    // Field
    private final Integer id;
    private final String code;
    private final String name;
    private final String market_name;

    // Constructor
    public StockListResponseDto(
            Integer id
            ,String code
            ,String name
            ,String market_name
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.market_name = market_name;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getMarket_name() {
        return market_name;
    }
}

package org.example.web.master.currency.domain;

public class CurrencyResponseDto {
    private final Integer id;
    private final String code;
    private final String symbol;

    public CurrencyResponseDto(Integer id, String code, String symbol) {
        this.id = id;
        this.code = code;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }
}

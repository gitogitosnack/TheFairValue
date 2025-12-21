package org.example.web.stock.list.domain;

public class StockEntity {

    private String code;
    private String name;
    private String market;

    // Constructor with 3 arguments
    public StockEntity(String code, String name, String market) {
        this.code = code;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
        this.name = name;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
        this.market = market;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
    }

    // Getter and Setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}

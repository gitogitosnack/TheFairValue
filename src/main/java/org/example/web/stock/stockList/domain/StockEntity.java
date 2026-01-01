package org.example.web.stock.stockList.domain;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(immutable = true)
@Table(name = "stock_list")
public class StockEntity {

    // Field
    @Id
    private final Integer id;
    private final String code;
    private final String stock_name;
    private final String market;
    private final LocalDateTime created_at;

    // Constructor with 3 arguments
    public StockEntity(
            Integer id
            , String code
            , String stock_name
            , String market
            , LocalDateTime created_at
    ) {
        this.id = id;
        this.code = code;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
        this.stock_name = stock_name;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
        this.market = market;   // ← コンストラクターでキャッチした後、値をこのクラスのフィールドに入れ忘れると値が画面に表示されない。
        this.created_at = created_at;
    }

    // Getter
    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getMarket() {
        return market;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

}
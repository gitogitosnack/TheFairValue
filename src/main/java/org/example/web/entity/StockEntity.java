package org.example.web.entity;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "companies")
public class StockEntity {

    // Field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;
    @Column(name = "code")
    private final String code;
    @Column(name = "name")
    private final String name;
    @Column(name = "country_id")
    private final Integer country_id;
    @Column(name = "industry_id")
    private final Integer industry_id;
    @Column(name = "market_name")
    private final String market_name;
    @Column(name = "currency_id")
    private final Integer currency_id;
    @Column(name = "delete_flg")
    private final Integer delete_flg;

    // Constructor with 3 arguments
    public StockEntity(
            Integer id
            ,String code   // ★ フィールド名と完全に一致させる
            ,String name
            ,Integer country_id    // ★ フィールド名と完全に一致させる
            ,Integer industry_id   // ★ フィールド名と完全に一致させる
            ,String market_name    // ★ フィールド名と完全に一致させる
            ,Integer currency_id   // ★ フィールド名と完全に一致させる
            ,Integer delete_flg    // ★ フィールド名と完全に一致させる
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.country_id = country_id;
        this.industry_id = industry_id;
        this.market_name = market_name;
        this.currency_id = currency_id;
        this.delete_flg = delete_flg;
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

    public Integer getCountry_id() {
        return country_id;
    }

    public Integer getIndustry_id() {
        return industry_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public Integer getCurrency_id() {
        return currency_id;
    }

    public Integer getDelete_flg() {
        return delete_flg;
    }
}
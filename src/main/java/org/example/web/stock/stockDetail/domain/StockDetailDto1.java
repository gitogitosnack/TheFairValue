package org.example.web.stock.stockDetail.domain;

import org.example.web.entity.CompanyEntity;

import java.math.BigDecimal;

public class StockDetailDto1 {
    // 画面の上半分の基本情報を取得するDao

    private String companyCode;
    private String companyName;
    private String calcurationId;
    private String calcurationName;
    private Integer currentPrice;
    private BigDecimal fairValue;
    private BigDecimal per;
    private BigDecimal pbr;
    private BigDecimal dividendYield;
    private BigDecimal equityRatio;



    // Getter and Setter

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCalcurationId() {
        return calcurationId;
    }

    public void setCalcurationId(String calcurationId) {
        this.calcurationId = calcurationId;
    }

    public String getCalcurationName() {
        return calcurationName;
    }

    public void setCalcurationName(String calcurationName) {
        this.calcurationName = calcurationName;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getFairValue() {
        return fairValue;
    }

    public void setFairValue(BigDecimal fairValue) {
        this.fairValue = fairValue;
    }

    public BigDecimal getPer() {
        return per;
    }

    public void setPer(BigDecimal per) {
        this.per = per;
    }

    public BigDecimal getPbr() {
        return pbr;
    }

    public void setPbr(BigDecimal pbr) {
        this.pbr = pbr;
    }

    public BigDecimal getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(BigDecimal dividendYield) {
        this.dividendYield = dividendYield;
    }

    public BigDecimal getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(BigDecimal equityRatio) {
        this.equityRatio = equityRatio;
    }
}

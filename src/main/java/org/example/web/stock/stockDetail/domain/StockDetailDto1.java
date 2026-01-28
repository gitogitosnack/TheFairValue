package org.example.web.stock.stockDetail.domain;

public class StockDetailDto1 {
    // 画面の上半分の基本情報を取得するDao

    private String companyCode;
    private String companyName;



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
}

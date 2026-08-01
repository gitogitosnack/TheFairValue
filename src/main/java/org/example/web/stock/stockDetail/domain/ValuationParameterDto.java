package org.example.web.stock.stockDetail.domain;

import java.math.BigDecimal;

public class ValuationParameterDto {
    private Integer parameterId;
    private String parameterCode;
    private String parameterName; // 例: "WACC", "加重平均資本コスト"
    private Integer displayOrder;
    private BigDecimal defaultValue;
    private BigDecimal fcf; // financial statement data

    public BigDecimal getFcf() {
        return fcf;
    }

    public void setFcf(BigDecimal fcf) {
        this.fcf = fcf;
    }

    // getter and setter
    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public BigDecimal getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(BigDecimal defaultValue) {
        this.defaultValue = defaultValue;
    }
}

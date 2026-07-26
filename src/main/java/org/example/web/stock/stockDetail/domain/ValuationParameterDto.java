package org.example.web.stock.stockDetail.domain;

import java.math.BigDecimal;

public class ValuationParameterDto {
    private Integer parameterId;

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

    public BigDecimal getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(BigDecimal defaultValue) {
        this.defaultValue = defaultValue;
    }

    private String parameterCode; // 例: "WACC"
    private String parameterName; // 例: "加重平均資本コスト"
    private BigDecimal defaultValue;

}

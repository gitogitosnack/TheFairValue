package org.example.web.stock.stockDetail.domain;

import java.math.BigDecimal;
import java.util.List;

public class TheoreticalPriceValuationDto {

    // simulation parameters
    private BigDecimal wacc;
    private BigDecimal growthRate5Years;
    private BigDecimal perpetualGrowthRate;

    // financial statement data
    private List<BigDecimal> fcf;

    public BigDecimal getWacc() {
        return wacc;
    }

    public void setWacc(BigDecimal wacc) {
        this.wacc = wacc;
    }

    public BigDecimal getGrowthRate5Years() {
        return growthRate5Years;
    }

    public void setGrowthRate5Years(BigDecimal growthRate5Years) {
        this.growthRate5Years = growthRate5Years;
    }

    public BigDecimal getPerpetualGrowthRate() {
        return perpetualGrowthRate;
    }

    public void setPerpetualGrowthRate(BigDecimal perpetualGrowthRate) {
        this.perpetualGrowthRate = perpetualGrowthRate;
    }

    public List<BigDecimal> getFcf() {
        return fcf;
    }

    public void setFcf(List<BigDecimal> fcf) {
        this.fcf = fcf;
    }

}

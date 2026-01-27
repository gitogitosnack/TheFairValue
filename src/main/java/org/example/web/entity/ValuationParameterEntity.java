package org.example.web.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(immutable = false)
@Table(name = "valuation_parameters")
public class ValuationParameterEntity {
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "wacc")
    private BigDecimal wacc;

    @Column(name = "terminal_growth_rate")
    private BigDecimal terminalGrowthRate;

    @Column(name = "beta")
    private BigDecimal beta;

    @Column(name = "cost_of_equity")
    private BigDecimal costOfEquity;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    // Getter and Setter
    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
    public BigDecimal getWacc() { return wacc; }
    public void setWacc(BigDecimal wacc) { this.wacc = wacc; }
    public BigDecimal getTerminalGrowthRate() { return terminalGrowthRate; }
    public void setTerminalGrowthRate(BigDecimal terminalGrowthRate) { this.terminalGrowthRate = terminalGrowthRate; }
    public BigDecimal getBeta() { return beta; }
    public void setBeta(BigDecimal beta) { this.beta = beta; }
    public BigDecimal getCostOfEquity() { return costOfEquity; }
    public void setCostOfEquity(BigDecimal costOfEquity) { this.costOfEquity = costOfEquity; }
    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}
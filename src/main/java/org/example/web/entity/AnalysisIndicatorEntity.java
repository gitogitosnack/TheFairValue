package org.example.web.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.math.BigDecimal;

/**
 * 分析指標情報のエンティティ
 */
@Entity(immutable = false)
@Table(name = "analysis_indicators")
public class AnalysisIndicatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fiscal_year")
    private Integer fiscalYear;

    @Column(name = "fiscal_quarter")
    private Integer fiscalQuarter;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "gross_margin")
    private BigDecimal grossMargin;

    @Column(name = "net_margin")
    private BigDecimal netMargin;

    @Column(name = "sga_ratio")
    private BigDecimal sgaRatio;

    @Column(name = "roa")
    private BigDecimal roa;

    @Column(name = "roe")
    private BigDecimal roe;

    @Column(name = "eps")
    private BigDecimal eps;

    @Column(name = "asset_turnover")
    private BigDecimal assetTurnover;

    @Column(name = "inventory_turnover")
    private BigDecimal inventoryTurnover;

    @Column(name = "ar_turnover")
    private BigDecimal arTurnover;

    @Column(name = "de_ratio")
    private BigDecimal deRatio;

    @Column(name = "debt_ratio")
    private BigDecimal debtRatio;

    @Column(name = "equity_ratio")
    private BigDecimal equityRatio;

    @Column(name = "financial_leverage")
    private BigDecimal financialLeverage;

    @Column(name = "interest_coverage")
    private BigDecimal interestCoverage;

    @Column(name = "op_cf_margin")
    private BigDecimal opCfMargin;

    @Column(name = "fcf")
    private BigDecimal fcf;

    @Column(name = "fin_cf")
    private BigDecimal finCf;

    @Column(name = "dividend_yield")
    private BigDecimal dividendYield;

    @Column(name = "payout_ratio")
    private BigDecimal payoutRatio;

    @Column(name = "per")
    private BigDecimal per;

    @Column(name = "pbr")
    private BigDecimal pbr;

    @Column(name = "intrinsic_value_gap")
    private BigDecimal intrinsicValueGap;

    // --- Getter and Setter ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Integer getFiscalQuarter() {
        return fiscalQuarter;
    }

    public void setFiscalQuarter(Integer fiscalQuarter) {
        this.fiscalQuarter = fiscalQuarter;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(BigDecimal grossMargin) {
        this.grossMargin = grossMargin;
    }

    public BigDecimal getNetMargin() {
        return netMargin;
    }

    public void setNetMargin(BigDecimal netMargin) {
        this.netMargin = netMargin;
    }

    public BigDecimal getSgaRatio() {
        return sgaRatio;
    }

    public void setSgaRatio(BigDecimal sgaRatio) {
        this.sgaRatio = sgaRatio;
    }

    public BigDecimal getRoa() {
        return roa;
    }

    public void setRoa(BigDecimal roa) {
        this.roa = roa;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getAssetTurnover() {
        return assetTurnover;
    }

    public void setAssetTurnover(BigDecimal assetTurnover) {
        this.assetTurnover = assetTurnover;
    }

    public BigDecimal getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(BigDecimal inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    public BigDecimal getArTurnover() {
        return arTurnover;
    }

    public void setArTurnover(BigDecimal arTurnover) {
        this.arTurnover = arTurnover;
    }

    public BigDecimal getDeRatio() {
        return deRatio;
    }

    public void setDeRatio(BigDecimal deRatio) {
        this.deRatio = deRatio;
    }

    public BigDecimal getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(BigDecimal debtRatio) {
        this.debtRatio = debtRatio;
    }

    public BigDecimal getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(BigDecimal equityRatio) {
        this.equityRatio = equityRatio;
    }

    public BigDecimal getFinancialLeverage() {
        return financialLeverage;
    }

    public void setFinancialLeverage(BigDecimal financialLeverage) {
        this.financialLeverage = financialLeverage;
    }

    public BigDecimal getInterestCoverage() {
        return interestCoverage;
    }

    public void setInterestCoverage(BigDecimal interestCoverage) {
        this.interestCoverage = interestCoverage;
    }

    public BigDecimal getOpCfMargin() {
        return opCfMargin;
    }

    public void setOpCfMargin(BigDecimal opCfMargin) {
        this.opCfMargin = opCfMargin;
    }

    public BigDecimal getFcf() {
        return fcf;
    }

    public void setFcf(BigDecimal fcf) {
        this.fcf = fcf;
    }

    public BigDecimal getFinCf() {
        return finCf;
    }

    public void setFinCf(BigDecimal finCf) {
        this.finCf = finCf;
    }

    public BigDecimal getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(BigDecimal dividendYield) {
        this.dividendYield = dividendYield;
    }

    public BigDecimal getPayoutRatio() {
        return payoutRatio;
    }

    public void setPayoutRatio(BigDecimal payoutRatio) {
        this.payoutRatio = payoutRatio;
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

    public BigDecimal getIntrinsicValueGap() {
        return intrinsicValueGap;
    }

    public void setIntrinsicValueGap(BigDecimal intrinsicValueGap) {
        this.intrinsicValueGap = intrinsicValueGap;
    }
}
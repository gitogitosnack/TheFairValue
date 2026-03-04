package org.example.web.stock.stockDetail.service;

import org.example.web.dao.AnalysisIndicatorDao;
import org.example.web.dao.CalculatedFairValueDao;
import org.example.web.dao.CompanyDao;
import org.example.web.dao.ValuationModelDao;
import org.example.web.entity.AnalysisIndicatorEntity;
import org.example.web.entity.CalculatedFairValueEntity;
import org.example.web.entity.CompanyEntity;
import org.example.web.entity.ValuationModelEntity;
import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.StockDetailDto1;
import org.example.web.stock.stockDetail.domain.StockDetailDto2;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockDetailServiceImpl implements StockDetailService{

    // initialize the dto1
    StockDetailDto1 stockDetailDto1 = new StockDetailDto1();

    // initialize the dto2
    StockDetailDto2 stockDetailDto2 = new StockDetailDto2();

    @Autowired
    CompanyDao companyDao;

    @Autowired
    ValuationModelDao valuationModelDao;

    @Autowired
    CalculatedFairValueDao calculatedFairValueDao;

    @Autowired
    AnalysisIndicatorDao analysisIndicatorDao;

    public StockAnalysisResponse getComprehensiveAnalysis(String code){

// Get data in the upper side of the detail display.
        // Get the company code and name
        // Get the current_price
        this.getCompanyCodeAndName(code);

        // Get the calc model id and name
        this.getCalculationCodeAndName(code);

        // Get the calculated_fair_value
        this.getFairValue(code);

        // Get the 割安割高度(TBC)

        // Get the values for レーダーチャート from 総合診断用テーブル(TBC)

        // Get the per, pbr, dividend_yield, equity_ratio
        this.getMarketIndicators(code);

// Get data in the lower side of the detail display called dto2.
        // Get each analysis indicators for 5 years
        this.getAnalysisIndicators(code);

        // Set data1 and data2 to the StockAnalysisResponse class.
        StockAnalysisResponse response = new StockAnalysisResponse();
        response.setDto1(stockDetailDto1);
        response.setDto2(stockDetailDto2);
        return response;

    }

// --- 1. Upper Side Data (StockDetailDto1) ---

    // This function gets the company code and name.
    // Get the current_price
    void getCompanyCodeAndName(String code) {
        Optional<CompanyEntity> company = companyDao.selectByCode(code);
        if(company.isPresent()){
            // Set the company code to dto
            stockDetailDto1.setCompanyCode(company.get().getTickerSymbol());
            // Set the company name to dto
            stockDetailDto1.setCompanyName(company.get().getName());
            // Set the current price to dto
            stockDetailDto1.setCurrentPrice(company.get().getCurrentPrice());
        }
    }

    // This function gets the calc model and name.
    void getCalculationCodeAndName (String code) {
        Optional<ValuationModelEntity> calcModel = valuationModelDao.selectById(code);
        if(calcModel.isPresent()){
            // Set the calcModel id to dto
            stockDetailDto1.setCalcurationId(calcModel.get().getId());
            // Set the calcModel name to dto
            stockDetailDto1.setCalcurationName(calcModel.get().getModelName());
        }
    }

    // Get the calculated_fair_value
    void getFairValue(String code) {
        Optional<CalculatedFairValueEntity> fairValue = calculatedFairValueDao.selectById(code);
        if (fairValue.isPresent()) {
            stockDetailDto1.setFairValue(fairValue.get().getFairValue());
        }
    }

    // Get the 割安割高度

    // Get the values for レーダーチャート from 総合診断用テーブル

    // Get the per, pbr, dividend_yield, equity_ratio

// Need to provide info of which year you want to get from DB.
    void getMarketIndicators(String code) {
        // get this year info and quoter.ex)2025,Q4

        Optional<AnalysisIndicatorEntity> indicator = analysisIndicatorDao.selectById(code);
        if (indicator.isPresent()) {
            stockDetailDto1.setPer(indicator.get().getPer());
            stockDetailDto1.setPbr(indicator.get().getPbr());
            stockDetailDto1.setDividendYield(indicator.get().getDividendYield());
            stockDetailDto1.setEquityRatio(indicator.get().getEquityRatio());
        }
    }

// --- 2. Lower Side Data (StockDetailDto2 - 5 Years History) ---

    // Get the analysis indicators for 5 years to use those dato to lower tables.
    void getAnalysisIndicators(String code) {
        // Data to be transferred Dao to Entity.
        List<AnalysisIndicatorEntity> indicators = analysisIndicatorDao.selectByCode(code);

        if (!indicators.isEmpty()) {

            // set the label name for each table and their rows.
            this.setupLabels(stockDetailDto2);

            // initialize each list
            List<String> fiscalYearLabels = new ArrayList<>();
            List<Double> roeList = new ArrayList<>();
            List<Double> grossMarginList = new ArrayList<>();
            List<Double> netMarginList = new ArrayList<>();
            List<Double> epsList = new ArrayList<>();
            List<Double> assetTurnoverList = new ArrayList<>();
            List<Double> inventoryTurnoverList = new ArrayList<>();
            List<Double> receivablesTurnoverList = new ArrayList<>();
            List<Double> equityRatioList = new ArrayList<>();
            List<Double> debtEquityRatioList = new ArrayList<>();
            List<Double> interestCoverageRatioList = new ArrayList<>();
            List<Double> fcfList = new ArrayList<>();
            List<Double> operationCfMarginList = new ArrayList<>();

            for (AnalysisIndicatorEntity entity : indicators) {
                // roe
                roeList.add(this.toDouble(entity.getRoe()));
                grossMarginList.add(this.toDouble(entity.getGrossMargin()));
                netMarginList.add(this.toDouble(entity.getNetMargin()));
                epsList.add(this.toDouble(entity.getEps()));

                // 2nd tab
                assetTurnoverList.add(this.toDouble(entity.getAssetTurnover()));
                inventoryTurnoverList.add(this.toDouble(entity.getInventoryTurnover()));
                receivablesTurnoverList.add(this.toDouble(entity.getArTurnover()));

                // 3rd tab
                equityRatioList.add(this.toDouble(entity.getEquityRatio()));
                debtEquityRatioList.add(this.toDouble(entity.getDeRatio()));
                interestCoverageRatioList.add(this.toDouble(entity.getInterestCoverage()));

                // 4th tab
                fcfList.add(this.toDouble(entity.getFcf()));
                operationCfMarginList.add(this.toDouble(entity.getOpCfMargin()));
            }

            // for文でデータを1つずつ格納されたListをDTOにセット
            stockDetailDto2.setFiscalYearLabels(fiscalYearLabels);
            stockDetailDto2.setRoeList(roeList);
            stockDetailDto2.setGrossMarginList(grossMarginList);
            stockDetailDto2.setNetMarginList(netMarginList);
            stockDetailDto2.setEpsList(epsList);
            stockDetailDto2.setAssetTurnoverList(assetTurnoverList);
            stockDetailDto2.setInventoryTurnoverList(inventoryTurnoverList);
            stockDetailDto2.setReceivablesTurnoverList(receivablesTurnoverList);
            stockDetailDto2.setEquityRatioList(equityRatioList);
            stockDetailDto2.setDebtEquityRatioList(debtEquityRatioList);
            stockDetailDto2.setInterestCoverageRatioList(interestCoverageRatioList);
            stockDetailDto2.setFcfList(fcfList);
            stockDetailDto2.setOperationCfMarginList(operationCfMarginList);
        } // end of for文
    } // end of this method

    // function to set the label name for each table and their rows.
    private void setupLabels(StockDetailDto2 dto2) {
        dto2.setTableTitle("財務分析指標（5期推移）");
        dto2.setRoeLabel("ROE (%)");
        dto2.setGrossMarginLabel("売上高総利益率 (%)");
        dto2.setNetMarginLabel("売上高純利益率 (%)");
        dto2.setEpsLabel("EPS (円)");
        dto2.setAssetTurnoverLabel("総資産回転率 (回)");
        dto2.setInventoryTurnoverLabel("棚卸資産回転率 (回)");
        dto2.setReceivablesTurnoverLabel("売上債権回転率 (回)");
        dto2.setEquityRatioLabel("自己資本比率 (%)");
        dto2.setDebtEquityRatioLabel("D/Eレシオ (倍)");
        dto2.setInterestCoverageRatioLabel("インタレスト・カバレッジ・レシオ (倍)");
        dto2.setFcfLabel("フリーキャッシュフロー (百万円)");
        dto2.setOperationCfMarginLabel("営業CFマージン (%)");
    }

    // BigDecimalをDoubleに安全に変換する補助メソッド
    private Double toDouble(BigDecimal val) {
        return val == null ? null : val.doubleValue();
    }

} // end of this class

package org.example.web.stock.stockDetail.service;

import org.example.web.dao.AnalysisIndicatorDao;
import org.example.web.dao.CalculatedFairValueDao;
import org.example.web.dao.CompanyDao;
import org.example.web.dao.ValuationModelDao;
import org.example.web.entity.AnalysisIndicatorEntity;
import org.example.web.entity.CalculatedFairValueEntity;
import org.example.web.entity.CompanyEntity;
import org.example.web.entity.ValuationModelEntity;
import org.example.web.stock.common.service.CIMapper;
import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.StockDetailDto1;
import org.example.web.stock.stockDetail.domain.StockDetailDto2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockDetailServiceImpl implements StockDetailService{

	// 本日の日付から「年」を取得して入れる
//    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    private static final int CURRENT_YEAR = 2025;
	private static final String FISCAL_QUARTER_Q4 = "Q4";
	private static final int DEFAULT_DISPLAY_YEARS_COUNT = 5;
	
    // initialize the dto1
    StockDetailDto1 stockDetailDto1 = new StockDetailDto1();

    // initialize the dto2
    StockDetailDto2 stockDetailDto2 = new StockDetailDto2();
    
    @Autowired
    CIMapper ciMapper;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    ValuationModelDao valuationModelDao;

    @Autowired
    CalculatedFairValueDao calculatedFairValueDao;

    @Autowired
    AnalysisIndicatorDao analysisIndicatorDao;
    
//  ====================================================
//--- 0. main process for getting company details indicators ---
//  ====================================================

    public StockAnalysisResponse getComprehensiveAnalysis(String code){

// Get data in the upper side of the detail display.
    	
    	// どのテーブルのIDが欲しいかに合わせて、テーブル名を固定値で渡す
        Integer companyId = ciMapper.convertCodeToId("companies", code);
    	
        // Get the company code and name
        // Get the current_price
        this.getCompanyCodeAndName(companyId);

        // Get the calc model id and name
        this.getCalculationCodeAndName(companyId);
        

        // Get the calculated_fair_value
        this.getFairValue(companyId);

        // Get the 割安割高度(TBC)

        // Get the values for レーダーチャート from 総合診断用テーブル(TBC)

        // Get the per, pbr, dividend_yield, equity_ratio
        this.getMarketIndicators(companyId);

// Get data in the lower side of the detail display called dto2.
        // Get each analysis indicators for 5 years
        this.getAnalysisIndicators(companyId);

        // Set data1 and data2 to the StockAnalysisResponse class.
        StockAnalysisResponse response = new StockAnalysisResponse();
        response.setDto1(stockDetailDto1);
        response.setDto2(stockDetailDto2);
        return response;

    }

//  ====================================================
// --- 1. Upper Side Data (StockDetailDto1) ---
//  ====================================================

    // This function gets the company code and name.
    // Get the current_price
    void getCompanyCodeAndName(Integer id) {
        Optional<CompanyEntity> company = companyDao.selectById(id);
        if(company.isPresent()){
            // Set the company code to dto
            stockDetailDto1.setCompanyCode(company.get().getCode());
            // Set the company name to dto
            stockDetailDto1.setCompanyName(company.get().getName());
            // Set the current price to dto
            stockDetailDto1.setCurrentPrice(company.get().getCurrentPrice());
        }
    }

    // This function gets the calc model and name.
    void getCalculationCodeAndName (Integer id) {
        Optional<ValuationModelEntity> calcModel = valuationModelDao.selectById(id);
        if(calcModel.isPresent()){
            // Set the calcModel id to dto / String.valueOf() or Integer.toString()
            stockDetailDto1.setCalcurationId(String.valueOf(calcModel.get().getId()));
            // Set the calcModel name to dto
            stockDetailDto1.setCalcurationName(calcModel.get().getModelName());
        }
    }

    // Get the calculated_fair_value
    void getFairValue(Integer id) {
        Optional<CalculatedFairValueEntity> fairValue = calculatedFairValueDao.selectById(id);
        if (fairValue.isPresent()) {
            stockDetailDto1.setFairValue(fairValue.get().getFairValue());
        }
    }


    // Get the 割安割高度

    // Get the values for レーダーチャート from 総合診断用テーブル

    // Get the per, pbr, dividend_yield, equity_ratio

// Need to provide info of which year you want to get from DB.
    void getMarketIndicators(Integer id) {
        // get this year info and quoter.ex)2025,Q4

        Optional<AnalysisIndicatorEntity> indicator = analysisIndicatorDao.selectById(id, CURRENT_YEAR, FISCAL_QUARTER_Q4);
        if (indicator.isPresent()) {
            stockDetailDto1.setPer(indicator.get().getPer());
            stockDetailDto1.setPbr(indicator.get().getPbr());
            stockDetailDto1.setDividendYield(indicator.get().getDividendYield());
            stockDetailDto1.setEquityRatio(indicator.get().getEquityRatio());
        }
    }

//  ====================================================
// --- 2. Lower Side Data (StockDetailDto2 - 5 Years History) ---
//  ====================================================
    // Get the analysis indicators for 5 years to use those dato to lower tables.
    void getAnalysisIndicators(Integer id) {
        // Data to be transferred Dao to Entity.
        List<AnalysisIndicatorEntity> indicators = analysisIndicatorDao.selectByCompanyId(id, DEFAULT_DISPLAY_YEARS_COUNT);

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
            	// 年度ラベルの追加 (例: "2025" を String として格納)
                fiscalYearLabels.add(String.valueOf(entity.getFiscalYear()));
                
                // 1st tab
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

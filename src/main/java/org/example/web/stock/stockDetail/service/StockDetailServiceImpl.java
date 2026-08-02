package org.example.web.stock.stockDetail.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.web.dao.AnalysisIndicatorDao;
import org.example.web.dao.AnalysisIndicatorDaoImpl;
import org.example.web.dao.CompanyDao;
import org.example.web.dao.CompanyValuationModelsDao;
import org.example.web.dao.CompanyValuationParameterDefaultsDao;
import org.example.web.dao.ValuationModelsDao;
import org.example.web.dao.ValuationParametersDao;
import org.example.web.entity.AnalysisIndicatorEntity;
import org.example.web.entity.CompanyEntity;
import org.example.web.entity.CompanyValuationModelsEntity;
import org.example.web.entity.CompanyValuationParameterDefaultsEntity;
import org.example.web.entity.ValuationModelEntity;
import org.example.web.entity.ValuationParametersEntity;
import org.example.web.stock.common.service.CIMapper;
import org.example.web.stock.stockDetail.domain.FinancialIndicatorDto;
import org.example.web.stock.stockDetail.domain.KeyFinancialIndicatorDto;
import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.TheoreticalPriceValuationDto;
import org.example.web.stock.stockDetail.domain.ValuationModelDto;
import org.example.web.stock.stockDetail.domain.ValuationParameterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockDetailServiceImpl implements StockDetailService {

    private final AnalysisIndicatorDaoImpl analysisIndicatorDaoImpl;
    // 本日の日付から「年」を取得して入れる
    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    // private static final int CURRENT_YEAR = 2025;
    private static final String FISCAL_QUARTER_Q4 = "Q4";
    private static final int DEFAULT_DISPLAY_YEARS_COUNT = 5;

    // initialize the TheoreticalPriceValuationDto
    TheoreticalPriceValuationDto theoreticalPriceValuationDto = new TheoreticalPriceValuationDto();

    // initialize the keyFinancialIndicatorDto
    KeyFinancialIndicatorDto keyFinancialIndicatorDto = new KeyFinancialIndicatorDto();

    // initialize the FinancialIndicatorDto
    FinancialIndicatorDto financialIndicatorDto = new FinancialIndicatorDto();

    @Autowired
    CIMapper ciMapper;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    ValuationModelsDao valuationModelDao;

    @Autowired
    AnalysisIndicatorDao analysisIndicatorDao;

    @Autowired
    CompanyValuationParameterDefaultsDao companyValuationParameterDefaultsDao;

    @Autowired
    CompanyValuationModelsDao companyValuationModelsDao;

    @Autowired
    ValuationParametersDao valuationParameterDao;

    StockDetailServiceImpl(AnalysisIndicatorDaoImpl analysisIndicatorDaoImpl) {
        this.analysisIndicatorDaoImpl = analysisIndicatorDaoImpl;
    }

    // ====================================================
    // --- 0. main process for getting company details indicators ---
    // ====================================================

    public StockAnalysisResponse getComprehensiveAnalysis(String code) {

        // Get data in the upper side of the detail display.

        // どのテーブルのIDが欲しいかに合わせて、テーブル名を固定値で渡す
        Integer companyId = ciMapper.convertCodeToId("companies", code);

        // Get the company code and name
        // Get the current_price
        this.getCompanyCodeAndName(companyId);

        // Get the parameters for each model and their default values from the
        // company_valuation_parameter_defaults table.
        this.getDefaultModelsAndParamValues(companyId);

        // Get the per, pbr, dividend_yield, equity_ratio
        this.getMarketIndicators(companyId);

        // Get data in the lower side of the detail display called
        // FinancialIndicatorDto.
        // Get each analysis indicators for 5 years
        this.getAnalysisIndicators(companyId);

        // Set data1 and data2 to the StockAnalysisResponse class.
        StockAnalysisResponse response = new StockAnalysisResponse();
        response.setTheoreticalPriceValuationDto(theoreticalPriceValuationDto);
        response.setKeyFinancialIndicatorDto(keyFinancialIndicatorDto);
        response.setFinancialIndicatorDto(financialIndicatorDto);
        return response;

    }

    // ====================================================
    // --- 1. Upper Side Data (KeyFinancialIndicatorDto) ---
    // ====================================================

    // This function gets the company code and name.
    // Get the current_price
    void getCompanyCodeAndName(Integer id) {
        Optional<CompanyEntity> company = companyDao.selectById(id);
        if (company.isPresent()) {
            // Set the company code to dto
            keyFinancialIndicatorDto.setCompanyCode(company.get().getCode());
            // Set the company name to dto
            keyFinancialIndicatorDto.setCompanyName(company.get().getName());
            // Set the current price to dto
            keyFinancialIndicatorDto.setCurrentPrice(company.get().getCurrentPrice());
        }
    }

    // Get the parameters to display in the company detail page.
    void getDefaultModelsAndParamValues(Integer id) {
        // get company id from company master table.
        Optional<CompanyEntity> company = companyDao.selectById(id);
        // 企業が存在しない場合 ➔ 空リストをセットして終了
        if (company.isEmpty()) {
            theoreticalPriceValuationDto.setModels(Collections.emptyList());
            return;
        }
        if (company.isPresent()) {
            // get models related to the company from company_valuation_models table.
            List<CompanyValuationModelsEntity> models = companyValuationModelsDao.selectById(id);
            // DBからnullまたは空リストが返ってきた場合 ➔ 空リストをセットして終了
            if (models == null || models.isEmpty()) {
                theoreticalPriceValuationDto.setModels(Collections.emptyList());
                return;
            }
            // prepare the valuation model DTOs to send to the front-end.
            List<ValuationModelDto> valuationModelDtoList = new ArrayList<>();

            for (CompanyValuationModelsEntity model : models) {
                // prepare the valuation model DTO for each model and add it to the list.
                ValuationModelDto valuationModelDto = new ValuationModelDto();
                valuationModelDto.setModelId(model.getValuationModelId());

                // get the model name from the valuation_model master table using the model id.
                Optional<ValuationModelEntity> modelMaster = valuationModelDao.selectById(model.getValuationModelId());
                if (modelMaster.isPresent()) {
                    valuationModelDto.setModelName(modelMaster.get().getModelName());
                } else {
                    valuationModelDto.setModelName("Unknown Model Name");
                }
                // get the default parameters making up the model.
                List<ValuationParametersEntity> params = valuationParameterDao
                        .selectById(model.getValuationModelId());

                // prepare the param DTOs to send to the front-end.
                List<ValuationParameterDto> paramDtoList = new ArrayList<>();
                if (params != null && !params.isEmpty()) {
                    for (ValuationParametersEntity param : params) {
                        // Process each default parameter
                        ValuationParameterDto paramDto = new ValuationParameterDto();
                        paramDto.setParameterId(param.getId());
                        paramDto.setParameterName(param.getParameterName());
                        paramDto.setParameterCode(param.getParameterCode());
                        paramDto.setDisplayOrder(param.getDisplayOrder());
                        // get the default value of the param.
                        CompanyValuationParameterDefaultsEntity paramDefault = companyValuationParameterDefaultsDao
                                .selectByCompanyIdAndParamId(id, param.getId());
                        if (paramDefault != null && paramDefault.getDefaultValue() != null) {
                            paramDto.setDefaultValue(paramDefault.getDefaultValue());
                        } else {
                            paramDto.setDefaultValue(BigDecimal.ZERO);
                        }
                        paramDtoList.add(paramDto);
                    }
                }
                valuationModelDto.setParameters(paramDtoList);

                valuationModelDtoList.add(valuationModelDto);
            }
            // Set the valuation model DTO list to the keyFinancialIndicatorDto
            theoreticalPriceValuationDto.setModels(valuationModelDtoList);
        }
    }

    // Get the per, pbr, dividend_yield, equity_ratio

    void getMarketIndicators(Integer id) {
        // Get the latest fiscal year that has Q4 data in DB
        int row_count = 10;
        List<AnalysisIndicatorEntity> indicators = analysisIndicatorDao.selectByCompanyId(id, row_count);

        // Find the latest fiscal year with Q4 data
        Integer latestYear = indicators.stream()
                .filter(indicator -> FISCAL_QUARTER_Q4.equals(indicator.getFiscalQuarter()))
                .map(AnalysisIndicatorEntity::getFiscalYear)
                .max(Integer::compareTo)
                .orElse(CURRENT_YEAR);

        // Get market indicators for the latest Q4 year
        Optional<AnalysisIndicatorEntity> indicator = analysisIndicatorDao.selectById(id, latestYear,
                FISCAL_QUARTER_Q4);
        if (indicator.isPresent()) {
            keyFinancialIndicatorDto.setPer(indicator.get().getPer());
            keyFinancialIndicatorDto.setPbr(indicator.get().getPbr());
            keyFinancialIndicatorDto.setDividendYield(indicator.get().getDividendYield());
            keyFinancialIndicatorDto.setEquityRatio(indicator.get().getEquityRatio());
            // Set the fiscal year for reference
            keyFinancialIndicatorDto.setMainIndicatorYearLabel(latestYear);
        }
    }

    // ====================================================
    // --- 2. Lower Side Data (FinancialIndicatorDto - 5 Years History) ---
    // ====================================================
    // Get the analysis indicators for 5 years to use those data in lower tables.
    void getAnalysisIndicators(Integer id) {
        // Data fetch from DAO.
        List<AnalysisIndicatorEntity> indicators = analysisIndicatorDao.selectByCompanyId(id,
                DEFAULT_DISPLAY_YEARS_COUNT);

        // Set label names
        this.setupLabels(financialIndicatorDto);

        // make the map with key as fiscal year and value as the entity for easy access
        // when filling the lists for each indicator.
        Map<Integer, AnalysisIndicatorEntity> dataMap = new HashMap<>();
        for (AnalysisIndicatorEntity entity : indicators) {
            Integer year = entity.getFiscalYear();

            if (!dataMap.containsKey(year)) {
                dataMap.put(year, entity);
            }
        }

        // Determine the latest year in data; fallback to CURRENT_YEAR
        int latestYear = indicators.stream()
                .map(AnalysisIndicatorEntity::getFiscalYear)
                .max(Integer::compareTo)
                .orElse(CURRENT_YEAR);

        // For missing latest 5-year, use whatever the latest available year is.
        int endYear = latestYear;
        int startYear = endYear - (DEFAULT_DISPLAY_YEARS_COUNT - 1);

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

        for (int year = startYear; year <= endYear; year++) {
            fiscalYearLabels.add(String.valueOf(year));
            AnalysisIndicatorEntity entity = dataMap.get(year);

            if (entity != null) {
                roeList.add(this.toDouble(entity.getRoe()));
                grossMarginList.add(this.toDouble(entity.getGrossMargin()));
                netMarginList.add(this.toDouble(entity.getNetMargin()));
                epsList.add(this.toDouble(entity.getEps()));
                assetTurnoverList.add(this.toDouble(entity.getAssetTurnover()));
                inventoryTurnoverList.add(this.toDouble(entity.getInventoryTurnover()));
                receivablesTurnoverList.add(this.toDouble(entity.getArTurnover()));
                equityRatioList.add(this.toDouble(entity.getEquityRatio()));
                debtEquityRatioList.add(this.toDouble(entity.getDeRatio()));
                interestCoverageRatioList.add(this.toDouble(entity.getInterestCoverage()));
                fcfList.add(this.toDouble(entity.getFcf()));
                operationCfMarginList.add(this.toDouble(entity.getOpCfMargin()));
            } else {
                roeList.add(null);
                grossMarginList.add(null);
                netMarginList.add(null);
                epsList.add(null);
                assetTurnoverList.add(null);
                inventoryTurnoverList.add(null);
                receivablesTurnoverList.add(null);
                equityRatioList.add(null);
                debtEquityRatioList.add(null);
                interestCoverageRatioList.add(null);
                fcfList.add(null);
                operationCfMarginList.add(null);
            }
        }

        financialIndicatorDto.setFiscalYearLabels(fiscalYearLabels);
        financialIndicatorDto.setRoeList(roeList);
        financialIndicatorDto.setGrossMarginList(grossMarginList);
        financialIndicatorDto.setNetMarginList(netMarginList);
        financialIndicatorDto.setEpsList(epsList);
        financialIndicatorDto.setAssetTurnoverList(assetTurnoverList);
        financialIndicatorDto.setInventoryTurnoverList(inventoryTurnoverList);
        financialIndicatorDto.setReceivablesTurnoverList(receivablesTurnoverList);
        financialIndicatorDto.setEquityRatioList(equityRatioList);
        financialIndicatorDto.setDebtEquityRatioList(debtEquityRatioList);
        financialIndicatorDto.setInterestCoverageRatioList(interestCoverageRatioList);
        financialIndicatorDto.setFcfList(fcfList);
        financialIndicatorDto.setOperationCfMarginList(operationCfMarginList);
    } // end of this method

    // function to set the label name for each table and their rows.
    private void setupLabels(FinancialIndicatorDto dto) {
        // dto.setTableTitle("財務分析指標（5期推移）");
        dto.setRoeLabel("ROE (%)");
        dto.setGrossMarginLabel("売上高総利益率 (%)");
        dto.setNetMarginLabel("売上高純利益率 (%)");
        dto.setEpsLabel("EPS (円)");
        dto.setAssetTurnoverLabel("総資産回転率 (回)");
        dto.setInventoryTurnoverLabel("棚卸資産回転率 (回)");
        dto.setReceivablesTurnoverLabel("売上債権回転率 (回)");
        dto.setEquityRatioLabel("自己資本比率 (%)");
        dto.setDebtEquityRatioLabel("D/Eレシオ (倍)");
        dto.setInterestCoverageRatioLabel("インタレスト・カバレッジ・レシオ (倍)");
        dto.setFcfLabel("フリーキャッシュフロー (百万円)");
        dto.setOperationCfMarginLabel("営業CFマージン (%)");
    }

    // BigDecimalをDoubleに安全に変換する補助メソッド
    private Double toDouble(BigDecimal val) {
        return val == null ? null : val.doubleValue();
    }

} // end of this class

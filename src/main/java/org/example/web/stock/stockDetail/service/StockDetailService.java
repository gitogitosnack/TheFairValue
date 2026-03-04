package org.example.web.stock.stockDetail.service;

import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;

public interface StockDetailService {

    public StockAnalysisResponse getComprehensiveAnalysis(String code);

    // void getCompanyCodeAndName (String code);


}

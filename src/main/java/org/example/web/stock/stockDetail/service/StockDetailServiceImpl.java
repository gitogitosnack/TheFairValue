package org.example.web.stock.stockDetail.service;

import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.StockDetailDto1;
import org.example.web.stock.stockDetail.domain.StockDetailDto2;

public class StockDetailServiceImpl implements StockDetailService{

    public StockAnalysisResponse getComprehensiveAnalysis(Long code){

        // Get data in upper side of the detail display.
        StockDetailDto1 data1 = stockDetailDao1.findAll();

        // Get data in lower side of the detail display.
        StockDetailDto2 data2 = stockDetailDao2.findAll();

        // Set data1 and data2 to the StockAnalysisResponse class.
        return null;

    }
}

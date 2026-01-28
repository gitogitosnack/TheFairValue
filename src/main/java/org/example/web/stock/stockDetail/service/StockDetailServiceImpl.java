package org.example.web.stock.stockDetail.service;

import org.example.web.dao.CompanyDao;
import org.example.web.entity.CompanyEntity;
import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.StockDetailDto1;
import org.example.web.stock.stockDetail.domain.StockDetailDto2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StockDetailServiceImpl implements StockDetailService{

    @Autowired
    CompanyDao companyDao;

    @Autowired
    StockDetailDto1 stockDetailDto1;

    public StockAnalysisResponse getComprehensiveAnalysis(Long code){

        // Get the company code and name
        Optional<CompanyEntity> company = companyDao.selectByCode(code);
        if(company.isPresent()){
            // Set the company code to dto
            stockDetailDto1.setCompanyCode(company.get().getTickerSymbol());
            // Set the company name to dto
            stockDetailDto1.setCompanyName(company.get().getName());
        }

        // Get the calc model id and name


        // Get the calculated_fair_value

        // Get the 割安割高度

        // Get the values for レーダーチャート from 総合診断用テーブル

        // Get the current_price

        // Get the per, pbr, dividend_yield, equity_ratio

        // Get data in upper side of the detail display.
        StockDetailDto1 data1;

        // Get the roe for 5 years

        // Get the gross margin for 5 years

        // Get the net margin for 5 years

        // Get the eps for 5 years

        // Get the asset turnover for 5 years

        // Get the inventory turnover for 5 years

        // Get the 売上負債回転率 for 5 years

        // Get the equity_ratio for 5 years

        // Get the debt-equity ratio for 5 years

        // Get the icr(interest coverage ratio) for 5 years

        // Get the fcf for 5 years

        // Get the 営業CFマージン for 5 years

        // Get data in lower side of the detail display.
        StockDetailDto2 data2;

        // Set data1 and data2 to the StockAnalysisResponse class.

        return null;

    }
}

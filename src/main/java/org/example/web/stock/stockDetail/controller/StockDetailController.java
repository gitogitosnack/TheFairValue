package org.example.web.stock.stockDetail.controller;

import org.example.web.stock.stockDetail.domain.StockAnalysisResponse;
import org.example.web.stock.stockDetail.domain.StockDetailForm;
import org.example.web.stock.stockDetail.service.StockDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock-detail")
public class StockDetailController {

    final StockDetailService stockDetailService;

    StockDetailController(StockDetailService stockDetailService) {
        this.stockDetailService = stockDetailService;
    }

    @GetMapping("/{code}")
    public ModelAndView display(@PathVariable String code, StockDetailForm form, ModelAndView mav) {

        // 画面のHTMLを設定
        mav.setViewName("stock-detail/stock-detail002");

        // call the service class
        StockAnalysisResponse analysisData = stockDetailService.getComprehensiveAnalysis(code);
        mav.addObject("analysisData", analysisData);

        return mav;
    }

}

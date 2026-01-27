package org.example.web.stock.stockList.controller;

import org.example.web.stock.stockList.domain.StockListResponseDto;
import org.example.web.stock.stockList.service.StockListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/top")
public class StockListController {

    private final StockListService stockListService;

    // コンストラクタ：フィールドにfinalがあり、不変値と宣言しているため、
    // コンストラクターで外部から持ってきた部品stockListService(右辺)をthis.stockListService(左辺)に代入している。
    public StockListController(StockListService stockListService) {
        this.stockListService = stockListService;
    }

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("stock-list/stock-list");
        List<StockListResponseDto> list = stockListService.initialDispAll();
        mav.addObject("stockList", list);
        return mav;
    }
}

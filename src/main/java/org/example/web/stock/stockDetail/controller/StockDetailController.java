package org.example.web.stock.stockDetail.controller;

import org.example.web.stock.stockDetail.domain.StockDetailForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stock-detail")
public class StockDetailController {

    @GetMapping("/{id}")
    public ModelAndView display (@PathVariable("id") Long id, StockDetailForm form, ModelAndView mav) {
        mav.setViewName("stock-detail/stock-detail");
        return mav;
    }
}

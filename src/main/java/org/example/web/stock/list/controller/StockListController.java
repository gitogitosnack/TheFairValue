package org.example.web.stock.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/top")
public class StockListController {

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("stock-list/stock-list");
        return mav;
    }
}

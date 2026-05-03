package org.example.web.master.currency.controller;

import org.example.web.master.currency.domain.CurrencyResponseDto;
import org.example.web.master.currency.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("currency-list/currency-list");
        List<CurrencyResponseDto> currencies = currencyService.initialDispAll();
        mav.addObject("items", currencies);
        return mav;
    }
}

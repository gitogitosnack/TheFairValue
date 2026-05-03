package org.example.web.master.country.controller;

import org.example.web.master.country.domain.CountryResponseDto;
import org.example.web.master.country.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("country-list/country-list");
        List<CountryResponseDto> countries = countryService.initialDispAll();
        mav.addObject("items", countries);
        return mav;
    }
}

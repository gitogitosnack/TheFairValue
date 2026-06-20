package org.example.web.stock.country.controller;

import org.example.web.stock.country.domain.CountryForm;
import org.example.web.stock.country.service.CountryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest_countries")
public class CountryRestController {

    // @Autowiredは、コンストラクタ型でDIしているため、書かなくてOK！
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody CountryForm form) {
        countryService.insertCountryInfo(form);
    }
}

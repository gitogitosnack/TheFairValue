package org.example.web.stock.country.controller;

import org.example.web.stock.country.domain.CountryForm;
import org.example.web.stock.country.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CountryForm form) {
        try {
            countryService.updateCountryInfo(form);
            return ResponseEntity.ok("更新に成功しました");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
        }

    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            countryService.deleteCountryInfoById(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package org.example.web.stock.country.service;

import org.example.web.stock.country.domain.CountryForm;
import org.example.web.stock.country.domain.CountryResponseDto;
import org.example.web.stock.stockList.domain.StockListForm;

import java.util.List;

public interface CountryService {
    List<CountryResponseDto> initialDispAll();

    void insertCountryInfo(CountryForm form);

    void deleteCountryInfoById(Integer id);

    // void updateCountryInfo(CountryForm form);

}

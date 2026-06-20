package org.example.web.stock.country.service;

import org.example.web.stock.country.domain.CountryForm;
import org.example.web.stock.country.domain.CountryResponseDto;

import java.util.List;

public interface CountryService {
    List<CountryResponseDto> initialDispAll();

    public void insertCountryInfo(CountryForm form);

}

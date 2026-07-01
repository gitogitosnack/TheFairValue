package org.example.web.stock.currency.service;

import java.util.List;

import org.example.web.stock.currency.domain.CurrencyForm;
import org.example.web.stock.currency.domain.CurrencyResponseDto;

public interface CurrencyService {
    List<CurrencyResponseDto> initialDispAll();

    void insertCurrencyInfo(CurrencyForm form);

    void updateCurrencyInfo(CurrencyForm form);

    void deleteCurrencyInfoById(Integer id);
}

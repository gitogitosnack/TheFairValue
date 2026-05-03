package org.example.web.stock.currency.service;

import org.example.web.stock.currency.domain.CurrencyResponseDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyResponseDto> initialDispAll();
}

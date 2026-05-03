package org.example.web.master.currency.service;

import org.example.web.master.currency.domain.CurrencyResponseDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyResponseDto> initialDispAll();
}

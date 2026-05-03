package org.example.web.master.currency.service;

import org.example.web.dao.CurrencyDao;
import org.example.web.master.currency.domain.CurrencyResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDao currencyDao;

    public CurrencyServiceImpl(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    @Override
    public List<CurrencyResponseDto> initialDispAll() {
        return currencyDao.selectAll().stream()
                .map(entity -> new CurrencyResponseDto(
                        entity.getId(),
                        entity.getCode(),
                        entity.getSymbol()
                ))
                .toList();
    }
}

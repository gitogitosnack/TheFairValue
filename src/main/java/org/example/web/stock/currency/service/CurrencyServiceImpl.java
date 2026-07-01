package org.example.web.stock.currency.service;

import org.example.web.dao.CurrencyDao;
import org.example.web.stock.currency.domain.CurrencyForm;
import org.example.web.stock.currency.domain.CurrencyResponseDto;
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
                        entity.getSymbol()))
                .toList();
    }

    @Override
    public void insertCurrencyInfo(CurrencyForm form) {
        var entity = new org.example.web.entity.CurrencyEntity();
        entity.setId(null);
        entity.setCode(form.code());
        entity.setSymbol(form.symbol());
        currencyDao.insert(entity);
    }

    @Override
    public void updateCurrencyInfo(CurrencyForm form) {
        var optionalEntity = currencyDao.selectById(form.id());
        var entity = optionalEntity.orElseThrow(() -> new org.example.web.exception.NotFoundException("通貨が見つかりません。"));
        entity.setCode(form.code());
        entity.setSymbol(form.symbol());
        currencyDao.update(entity);
    }

    @Override
    public void deleteCurrencyInfoById(Integer id) {
        var optionalRecord = currencyDao.selectById(id);
        var record = optionalRecord.orElseThrow(() -> new org.example.web.exception.NotFoundException("通貨が見つかりません。"));
        currencyDao.delete(record);
    }
}

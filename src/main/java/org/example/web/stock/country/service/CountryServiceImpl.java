package org.example.web.stock.country.service;

import java.util.List;
import java.util.Optional;

import org.example.web.dao.CountryDao;
import org.example.web.entity.CountryEntity;
import org.example.web.exception.NotFoundException;
import org.example.web.stock.country.domain.CountryForm;
import org.example.web.stock.country.domain.CountryResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<CountryResponseDto> initialDispAll() {
        return countryDao.selectAll().stream()
                .map(entity -> new CountryResponseDto(
                        entity.getId(),
                        entity.getCode(),
                        entity.getName()))
                .toList();
    }

    @Override
    public void insertCountryInfo(CountryForm form) {
        CountryEntity entity = new CountryEntity();
        entity.setId(null);
        entity.setCode(form.code());
        entity.setName(form.name());

        countryDao.insert(entity);
    }

    @Override
    public void deleteCountryInfoById(Integer id) {
        Optional<CountryEntity> optionalRecord = countryDao.selectById(id);
        // orElseThrowを使って、中身（CountryEntity）を取り出す
        CountryEntity record = optionalRecord
                .orElseThrow(() -> new NotFoundException("国が見つかりません。"));
        countryDao.delete(record);

    }

}

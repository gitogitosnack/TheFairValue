package org.example.web.stock.country.service;

import org.example.web.dao.CountryDao;
import org.example.web.stock.country.domain.CountryResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                        entity.getName()
                ))
                .toList();
    }
}

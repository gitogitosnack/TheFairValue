package org.example.web.master.valuationmodel.service;

import org.example.web.dao.ValuationModelDao;
import org.example.web.master.valuationmodel.domain.ValuationModelResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ValuationModelServiceImpl implements ValuationModelService {

    private final ValuationModelDao valuationModelDao;

    public ValuationModelServiceImpl(ValuationModelDao valuationModelDao) {
        this.valuationModelDao = valuationModelDao;
    }

    @Override
    public List<ValuationModelResponseDto> initialDispAll() {
        return valuationModelDao.selectAll().stream()
                .map(entity -> new ValuationModelResponseDto(
                        entity.getId(),
                        entity.getModelName(),
                        entity.getFormulaDescription()
                ))
                .toList();
    }
}

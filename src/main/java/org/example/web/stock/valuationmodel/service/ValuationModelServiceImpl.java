package org.example.web.stock.valuationmodel.service;

import org.example.web.dao.ValuationModelsDao;
import org.example.web.stock.valuationmodel.domain.ValuationModelForm;
import org.example.web.stock.valuationmodel.domain.ValuationModelResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ValuationModelServiceImpl implements ValuationModelService {

    private final ValuationModelsDao valuationModelDao;

    public ValuationModelServiceImpl(ValuationModelsDao valuationModelDao) {
        this.valuationModelDao = valuationModelDao;
    }

    @Override
    public List<ValuationModelResponseDto> initialDispAll() {
        return valuationModelDao.selectAll().stream()
                .map(entity -> new ValuationModelResponseDto(
                        entity.getId(),
                        entity.getModelName(),
                        entity.getFormulaDescription()))
                .toList();
    }

    @Override
    public void insertValuationModelInfo(ValuationModelForm form) {
        var entity = new org.example.web.entity.ValuationModelEntity();
        entity.setId(null);
        entity.setModelName(form.modelName());
        entity.setFormulaDescription(form.formulaDescription());
        valuationModelDao.insert(entity);
    }

    @Override
    public void updateValuationModelInfo(ValuationModelForm form) {
        var optionalEntity = valuationModelDao.selectById(form.id());
        var entity = optionalEntity
                .orElseThrow(() -> new org.example.web.exception.NotFoundException("評価モデルが見つかりません。"));
        entity.setModelName(form.modelName());
        entity.setFormulaDescription(form.formulaDescription());
        valuationModelDao.update(entity);
    }

    @Override
    public void deleteValuationModelInfoById(Integer id) {
        var optionalRecord = valuationModelDao.selectById(id);
        var record = optionalRecord
                .orElseThrow(() -> new org.example.web.exception.NotFoundException("評価モデルが見つかりません。"));
        valuationModelDao.delete(record);
    }
}

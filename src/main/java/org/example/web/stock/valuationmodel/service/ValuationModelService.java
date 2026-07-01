package org.example.web.stock.valuationmodel.service;

import java.util.List;

import org.example.web.stock.valuationmodel.domain.ValuationModelForm;
import org.example.web.stock.valuationmodel.domain.ValuationModelResponseDto;

public interface ValuationModelService {
    List<ValuationModelResponseDto> initialDispAll();

    void insertValuationModelInfo(ValuationModelForm form);

    void updateValuationModelInfo(ValuationModelForm form);

    void deleteValuationModelInfoById(Integer id);
}

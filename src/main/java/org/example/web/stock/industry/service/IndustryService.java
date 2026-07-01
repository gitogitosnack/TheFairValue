package org.example.web.stock.industry.service;

import java.util.List;

import org.example.web.stock.industry.domain.IndustryForm;
import org.example.web.stock.industry.domain.IndustryResponseDto;

public interface IndustryService {
    List<IndustryResponseDto> initialDispAll();

    void insertIndustryInfo(IndustryForm form);

    void updateIndustryInfo(IndustryForm form);

    void deleteIndustryInfoById(Integer id);
}

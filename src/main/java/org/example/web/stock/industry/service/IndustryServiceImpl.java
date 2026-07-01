package org.example.web.stock.industry.service;

import org.example.web.dao.IndustryDao;
import org.example.web.stock.industry.domain.IndustryForm;
import org.example.web.stock.industry.domain.IndustryResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndustryServiceImpl implements IndustryService {

    private final IndustryDao industryDao;

    public IndustryServiceImpl(IndustryDao industryDao) {
        this.industryDao = industryDao;
    }

    @Override
    public List<IndustryResponseDto> initialDispAll() {
        return industryDao.selectAll().stream()
                .map(entity -> new IndustryResponseDto(
                        entity.getId(),
                        entity.getName(),
                        entity.getSectorName(),
                        entity.getDescription(),
                        entity.getAvgPer()))
                .toList();
    }

    @Override
    public void insertIndustryInfo(IndustryForm form) {
        var entity = new org.example.web.entity.IndustryEntity();
        entity.setId(null);
        entity.setName(form.name());
        entity.setSectorName(form.sectorName());
        entity.setDescription(form.description());
        entity.setAvgPer(form.avgPer());
        industryDao.insert(entity);
    }

    @Override
    public void updateIndustryInfo(IndustryForm form) {
        var optionalEntity = industryDao.selectById(form.id());
        var entity = optionalEntity.orElseThrow(() -> new org.example.web.exception.NotFoundException("業種が見つかりません。"));
        entity.setName(form.name());
        entity.setSectorName(form.sectorName());
        entity.setDescription(form.description());
        entity.setAvgPer(form.avgPer());
        industryDao.update(entity);
    }

    @Override
    public void deleteIndustryInfoById(Integer id) {
        var optionalRecord = industryDao.selectById(id);
        var record = optionalRecord.orElseThrow(() -> new org.example.web.exception.NotFoundException("業種が見つかりません。"));
        industryDao.delete(record);
    }
}

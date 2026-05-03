package org.example.web.master.industry.service;

import org.example.web.dao.IndustryDao;
import org.example.web.master.industry.domain.IndustryResponseDto;
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
                        entity.getAvgPer()
                ))
                .toList();
    }
}

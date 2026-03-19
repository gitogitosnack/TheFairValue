package org.example.web.dao;

import org.example.web.entity.AnalysisIndicatorEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface AnalysisIndicatorDao {
    @Select
    List<AnalysisIndicatorEntity> selectAll();

    @Select
    Optional<AnalysisIndicatorEntity> selectById(Integer company_id, int fiscal_year, String fiscal_quarter);

    @Select
    List<AnalysisIndicatorEntity> selectByCompanyId(Integer company_id, int display_years);

    @Insert
    int insert(AnalysisIndicatorEntity entity);

    @Update
    int update(AnalysisIndicatorEntity entity);

    @Delete
    int delete(AnalysisIndicatorEntity entity);
}
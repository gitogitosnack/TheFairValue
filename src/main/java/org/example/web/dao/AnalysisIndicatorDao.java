package org.example.web.dao;

import org.example.web.entity.AnalysisIndicatorEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface AnalysisIndicatorDao {
    @Select
    List<AnalysisIndicatorEntity> selectAll();

    @Select
    Optional<AnalysisIndicatorEntity> selectById(Integer id);

    @Insert
    Result<AnalysisIndicatorEntity> insert(AnalysisIndicatorEntity entity);

    @Update
    Result<AnalysisIndicatorEntity> update(AnalysisIndicatorEntity entity);

    @Delete
    Result<AnalysisIndicatorEntity> delete(AnalysisIndicatorEntity entity);
}
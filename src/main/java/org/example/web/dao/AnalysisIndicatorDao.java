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
    Optional<AnalysisIndicatorEntity> selectById(String id);

    @Select
    List<AnalysisIndicatorEntity> selectByCode(String code);

    @Insert
    int insert(AnalysisIndicatorEntity entity);

    @Update
    int update(AnalysisIndicatorEntity entity);

    @Delete
    int delete(AnalysisIndicatorEntity entity);
}
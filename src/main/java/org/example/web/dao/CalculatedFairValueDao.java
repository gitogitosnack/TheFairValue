package org.example.web.dao;

import org.example.web.entity.CalculatedFairValueEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface CalculatedFairValueDao {
    @Select
    List<CalculatedFairValueEntity> selectAll();

    @Select
    Optional<CalculatedFairValueEntity> selectById(Integer id);

    @Insert
    int insert(CalculatedFairValueEntity entity);

    @Update
    int update(CalculatedFairValueEntity entity);

    @Delete
    int delete(CalculatedFairValueEntity entity);
}
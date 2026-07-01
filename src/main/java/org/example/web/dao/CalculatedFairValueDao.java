package org.example.web.dao;

import java.util.List;
import java.util.Optional;

import org.example.web.entity.CalculatedFairValueEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

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
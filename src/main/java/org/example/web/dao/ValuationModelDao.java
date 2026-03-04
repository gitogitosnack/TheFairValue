package org.example.web.dao;

import org.example.web.entity.ValuationModelEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface ValuationModelDao {
    @Select
    List<ValuationModelEntity> selectAll();

    @Select
    Optional<ValuationModelEntity> selectById(String id);

    @Insert
    int insert(ValuationModelEntity entity);

    @Update
    int update(ValuationModelEntity entity);

    @Delete
    int delete(ValuationModelEntity entity);
}
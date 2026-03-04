package org.example.web.dao;

import org.example.web.entity.ValuationParameterEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface ValuationParameterDao {
    @Select
    List<ValuationParameterEntity> selectAll();

    @Select
    Optional<ValuationParameterEntity> selectById(Long id);

    @Insert
    int insert(ValuationParameterEntity entity);

    @Update
    int update(ValuationParameterEntity entity);

    @Delete
    int delete(ValuationParameterEntity entity);
}
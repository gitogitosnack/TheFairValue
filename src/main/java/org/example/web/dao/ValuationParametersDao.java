package org.example.web.dao;

import org.example.web.entity.ValuationParametersEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface ValuationParametersDao {
    @Select
    List<ValuationParametersEntity> selectAll();

    @Select
    List<ValuationParametersEntity> selectById(Integer id);

    @Insert
    int insert(ValuationParametersEntity entity);

    @Update
    int update(ValuationParametersEntity entity);

    @Delete
    int delete(ValuationParametersEntity entity);
}
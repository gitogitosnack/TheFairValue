package org.example.web.dao;

import java.util.List;
import java.util.Optional;

import org.example.web.entity.CurrencyEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface CurrencyDao {
    @Select
    List<CurrencyEntity> selectAll();

    @Select
    Optional<CurrencyEntity> selectById(int id);

    @Insert
    int insert(CurrencyEntity entity);

    @Update
    int update(CurrencyEntity entity);

    @Delete
    int delete(CurrencyEntity entity);
}
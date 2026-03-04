package org.example.web.dao;

import org.example.web.entity.CurrencyEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface CurrencyDao {
    @Select
    List<CurrencyEntity> selectAll();

    @Select
    Optional<CurrencyEntity> selectByCode(String currencyCode);

    @Insert
    int insert(CurrencyEntity entity);

    @Update
    int update(CurrencyEntity entity);

    @Delete
    int delete(CurrencyEntity entity);
}
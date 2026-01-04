package org.example.web.dao;


import org.example.web.stock.stockList.domain.StockEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;

@Dao
@ConfigAutowireable
public interface StockListDao {

    @Select
    List<StockEntity> findAll();

    @Select
    StockEntity selectById(Integer id);

    @Update
    Result<StockEntity> update(StockEntity entity);

    @Delete
    Result<StockEntity> delete(StockEntity entity);

    @Insert
    Result<StockEntity> insert(StockEntity entity);

}

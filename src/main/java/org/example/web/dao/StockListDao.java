package org.example.web.dao;


import org.example.web.stock.stockList.domain.StockEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;

@Dao
@ConfigAutowireable
public interface StockListDao {

    @Select
    List<StockEntity> findAll();

    @Select
    StockEntity selectById(String code);

    @Update
    Result<StockEntity> update(StockEntity entity);

}

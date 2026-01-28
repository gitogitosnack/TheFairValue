package org.example.web.dao;

import org.example.web.entity.DailyQuoteEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface DailyQuoteDao {
    @Select
    List<DailyQuoteEntity> selectAll();

    @Select
    Optional<DailyQuoteEntity> selectById(Long id);

    @Insert
    Result<DailyQuoteEntity> insert(DailyQuoteEntity entity);

    @Update
    Result<DailyQuoteEntity> update(DailyQuoteEntity entity);

    @Delete
    Result<DailyQuoteEntity> delete(DailyQuoteEntity entity);
}
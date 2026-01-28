package org.example.web.dao;

import org.example.web.entity.FinancialStatementEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface FinancialStatementDao {
    @Select
    List<FinancialStatementEntity> selectAll();

    @Select
    Optional<FinancialStatementEntity> selectById(Long id);

    @Insert
    Result<FinancialStatementEntity> insert(FinancialStatementEntity entity);

    @Update
    Result<FinancialStatementEntity> update(FinancialStatementEntity entity);

    @Delete
    Result<FinancialStatementEntity> delete(FinancialStatementEntity entity);
}
package org.example.web.dao;

import org.example.web.entity.CountryEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface CountryDao {
    @Select
    List<CountryEntity> selectAll();

    @Select
    Optional<CountryEntity> selectByCode(String countryCode);

    @Insert
    Result<CountryEntity> insert(CountryEntity entity);

    @Update
    Result<CountryEntity> update(CountryEntity entity);

    @Delete
    Result<CountryEntity> delete(CountryEntity entity);
}
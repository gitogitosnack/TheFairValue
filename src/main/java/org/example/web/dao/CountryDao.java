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
    int insert(CountryEntity entity);

    @Update
    int update(CountryEntity entity);

    @Delete
    int delete(CountryEntity entity);
}
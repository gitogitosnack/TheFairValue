package org.example.web.dao;

import org.example.web.entity.IndustryEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface IndustryDao {
    @Select
    List<IndustryEntity> selectAll();

    @Select
    Optional<IndustryEntity> selectById(Integer id);

    @Insert
    int insert(IndustryEntity entity);

    @Update
    int update(IndustryEntity entity);

    @Delete
    int delete(IndustryEntity entity);
}
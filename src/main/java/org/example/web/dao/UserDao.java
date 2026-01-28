package org.example.web.dao;

import org.example.web.entity.UserEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface UserDao {
    @Select
    List<UserEntity> selectAll();

    @Select
    Optional<UserEntity> selectById(Long id);

    @Insert
    Result<UserEntity> insert(UserEntity entity);

    @Update
    Result<UserEntity> update(UserEntity entity);

    @Delete
    Result<UserEntity> delete(UserEntity entity);
}
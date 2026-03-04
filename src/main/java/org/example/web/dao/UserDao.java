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
    int insert(UserEntity entity);

    @Update
    int update(UserEntity entity);

    @Delete
    int delete(UserEntity entity);
}
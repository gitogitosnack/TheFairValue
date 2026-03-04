package org.example.web.dao;

import org.example.web.entity.UserSimulationEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface UserSimulationDao {
    @Select
    List<UserSimulationEntity> selectAll();

    @Select
    Optional<UserSimulationEntity> selectById(Long id);

    @Insert
    int insert(UserSimulationEntity entity);

    @Update
    int update(UserSimulationEntity entity);

    @Delete
    int delete(UserSimulationEntity entity);
}
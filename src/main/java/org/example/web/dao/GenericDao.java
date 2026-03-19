package org.example.web.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable // Spring Boot連携用
public interface GenericDao {

    @Select
    Integer getIdByCode(String tableName, String code);
}
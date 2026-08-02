package org.example.web.dao;

import java.util.List;

import org.example.web.entity.CompanyValuationModelsEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface CompanyValuationModelsDao {

    @Select
    List<CompanyValuationModelsEntity> selectById(Integer company_id);

    @Insert
    int insert(CompanyValuationModelsEntity entity);

    @Update
    int update(CompanyValuationModelsEntity entity);
}
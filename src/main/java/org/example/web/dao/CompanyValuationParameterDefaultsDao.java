package org.example.web.dao;

import org.example.web.entity.CompanyValuationParameterDefaultsEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import java.util.List;
import java.util.Optional;

@Dao
@ConfigAutowireable
public interface CompanyValuationParameterDefaultsDao {

    /**
     * 複合主キーで1件検索します。
     * 
     * @param companyId 企業ID
     * @return 該当するパラメータデータ
     */
    @Select
    List<CompanyValuationParameterDefaultsEntity> selectById(Integer companyId);

    /**
     * データを登録します。
     * 
     * @param entity 登録対象のエンティティ
     * @return 更新件数
     */
    @Insert
    int insert(CompanyValuationParameterDefaultsEntity entity);

    /**
     * データを更新します。
     * 
     * @param entity 更新対象のエンティティ
     * @return 更新件数
     */
    @Update
    int update(CompanyValuationParameterDefaultsEntity entity);

    /**
     * データを削除します。
     * 
     * @param entity 削除対象のエンティティ
     * @return 更新件数
     */
    @Delete
    int delete(CompanyValuationParameterDefaultsEntity entity);

}
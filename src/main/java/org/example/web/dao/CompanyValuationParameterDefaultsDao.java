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
     * 複合主キーの内、企業IDのみで検索し、複数件取得する。
     * 
     * @param companyId 企業ID
     * @return 該当するパラメータデータ
     */
    @Select
    List<CompanyValuationParameterDefaultsEntity> selectById(Integer companyId);

    /**
     * 複合主キーで1件検索します。
     * 
     * @param companyId            企業ID
     * @param valuationParameterId パラメータID
     * @return 該当するパラメータデータ
     */
    @Select
    CompanyValuationParameterDefaultsEntity selectByCompanyIdAndParamId(Integer companyId,
            Integer valuationParameterId);

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
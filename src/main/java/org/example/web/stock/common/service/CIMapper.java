package org.example.web.stock.common.service;

import org.example.web.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CIMapper {

	 @Autowired
    private final GenericDao genericDao;

    // コンストラクタ
    public CIMapper(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * @param tableName 対象のテーブル名 (例: "companies")
     * @param code 検索したいコード (例: "AAPL")
     */
    public Integer convertCodeToId(String tableName, String code) {
        Integer id = genericDao.getIdByCode(tableName, code);
        
        if (id == null) {
            throw new RuntimeException(
                String.format("IDが見つかりません (Table: %s, Code: %s)", tableName, code)
            );
        }
        return id;
    }
}
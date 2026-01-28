package org.example.web.dao;
/* このDaoインターファイスは、画面のために最初の頃作成したファイルである。
本来、Daoインターファイスとは、DBテーブル毎に一個ずつ作成されるものであるため、
このDAOはいずれ消す。
*/


import org.example.web.entity.StockEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;

// @Dao:このインターフェースが DomaのDAOであることを示す最も重要なアノテーション。
// Domaはコンパイル時にこのアノテーション@Daoを検出し、データベース処理を具体的に記述した実装クラス（通常はStockListDaoImpl）を自動生成します。
@Dao

// このDAOを Spring BootなどのDIコンテナで管理可能にする ためのアノテーション.
// これを付けることで、サービス層などで@Autowiredを使ってこのDAOをDIできるようになります。
@ConfigAutowireable
public interface StockListDao {

    @Select
    List<StockEntity> findAll();  // List<StockEntity>なので、複数件の処理に対応。

    @Select
    StockEntity selectById(Integer id);  // StockEntityだけなので、一件だけの処理に対応。

    @Update
    Result<StockEntity> update(StockEntity entity);

    @Delete
    Result<StockEntity> delete(StockEntity entity);

    @Insert
    Result<StockEntity> insert(StockEntity entity);

}

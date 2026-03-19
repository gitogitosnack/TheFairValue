package org.example.web.stock.stockList.service;

import org.example.web.dao.StockListDao;
import org.example.web.entity.StockEntity;
import org.example.web.stock.stockList.domain.StockListForm;
import org.example.web.stock.stockList.domain.StockListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockListServiceImpl implements StockListService {

    // Define the fields here
    private final StockListDao stockListDao;

    // Define the constructor
    public StockListServiceImpl(StockListDao stockListDao) {
        this.stockListDao = stockListDao;
    }

    @Override
    public List<StockListResponseDto> initialDispAll() {

        // Get all stocks
        List<StockEntity> stocksHoldingByEntity = stockListDao.findAll();

        // Convert to the dto from entity
        List<StockListResponseDto> stocks = this.unloading(stocksHoldingByEntity);

        return stocks;
    }

    public List<StockListResponseDto> unloading(List<StockEntity> entity) {
        return entity.stream().map(record -> new StockListResponseDto(
                record.getId()
                ,record.getCode()
                ,record.getName()
                ,record.getMarket_name()
        )).toList();
    }

    @Override
    public void updateStockInfo(StockListForm form) {
        // 1. 現在のデータを取得
        StockEntity record = stockListDao.selectById(form.getId());

        if (record != null) {
            // 2. 画面からの入力値で上書き
        StockEntity entity = new StockEntity(
                form.getId()
                ,form.getCode()
                ,form.getName()
                ,1
                ,1
                ,form.getMarket_name()
                ,1
                ,0
                );
            // entity.setUpdatedAt(LocalDateTime.now()); // 更新日などがあれば

            // 3. 更新実行
            stockListDao.update(entity);
        }

    }

    @Override
    public void deleteStockInfoById(Integer id) {
        // 現在のデータを取得
        StockEntity record = stockListDao.selectById(id);

        if (record != null) {

            // 更新実行
            stockListDao.delete(record);
        }
    }

    @Override
    public void insertStockInfo(StockListForm form) {
            // 新規登録情報をセット
            StockEntity entity = new StockEntity(
                    null
                    ,form.getCode()
                    ,form.getName()
                    ,1
                    ,1
                    ,form.getMarket_name()
                    ,1
                    ,0
            );
            // entity.setCreatedAt(LocalDateTime.now()); // 登録日などがあれば

            // 新規登録処理実行
            stockListDao.insert(entity);


    }
}
package org.example.web.stock.stockList.service;

import org.example.web.dao.StockListDao;
import org.example.web.stock.stockList.domain.StockEntity;
import org.example.web.stock.stockList.domain.StockListForm;
import org.example.web.stock.stockList.domain.StockListResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
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

        // Convert to the dto from entity.
        List<StockListResponseDto> stocks = this.unloading(stocksHoldingByEntity);

        return stocks;
    }

    public List<StockListResponseDto> unloading(List<StockEntity> entity) {
        return entity.stream().map(record -> new StockListResponseDto(
                record.getCode()
                , record.getStock_name()
                , record.getMarket()
        )).toList();
    }

    public void updateStockInfo(StockListForm form) {
        // 1. 現在のデータを取得
        //StockEntity entity = stockListDao.selectById(form.getCode());

        //if (entity != null) {
            // 2. 画面からの入力値で上書き
        StockEntity entity = new StockEntity(
                1
                ,form.getCode()
                ,form.getStockName()
                ,form.getMarket()
                ,LocalDateTime.now()
                );
            // entity.setUpdatedAt(LocalDateTime.now()); // 更新日などがあれば

            // 3. 更新実行
            stockListDao.update(entity);
        //}

    }
}
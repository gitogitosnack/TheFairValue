package org.example.web.stock.stockList.service;

import org.example.web.stock.stockList.domain.StockEntity;
import org.example.web.stock.stockList.domain.StockListForm;
import org.example.web.stock.stockList.domain.StockListResponseDto;

import java.util.List;

public interface StockListService {

    List<StockListResponseDto> initialDispAll ();

    void updateStockInfo(StockListForm form);
}

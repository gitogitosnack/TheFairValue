package org.example.web.stock.list.service;

import org.example.web.stock.list.domain.StockEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockListServiceImpl implements StockListService {

    @Override
    public List<StockEntity> initialDispAll() {
        List<StockEntity> stocks = List.of(
                new StockEntity("1", "pickchu", "kato")
                ,new StockEntity("2", "bananaWani", "atagawa")
                ,new StockEntity("3", "pikmin", "kansai")
        );
        return stocks;
    }
}

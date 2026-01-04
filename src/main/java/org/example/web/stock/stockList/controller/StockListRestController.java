package org.example.web.stock.stockList.controller;

import org.example.web.stock.stockList.domain.StockListForm;
import org.example.web.stock.stockList.service.StockListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController // JSONを返すためのアノテーション
@RequestMapping("/rest_stock_list")
public class StockListRestController {

    @Autowired
    StockListService stockListService;

    @PostMapping("/insert")
    public void insert (@RequestBody StockListForm form) {
            stockListService.insertStockInfo(form);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update (@RequestBody StockListForm form) {
        try{
            stockListService.updateStockInfo(form);
            return ResponseEntity.ok("更新に成功しました");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            stockListService.deleteStockInfoById(id); // 削除ロジック
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

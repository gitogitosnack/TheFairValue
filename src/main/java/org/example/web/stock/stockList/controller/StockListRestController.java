package org.example.web.stock.stockList.controller;

import org.example.web.stock.stockList.domain.StockListForm;
import org.example.web.stock.stockList.service.StockListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSONを返すためのアノテーション
@RequestMapping("/rest_stock_list")
public class StockListRestController {

    // @Autowiredは、コンストラクタ型でDIしているため、書かなくてOK！
    // Lombokがない場合は、@RequiredArgsConstructorは使えない。
    // なので、コンストラクタもちゃんと書かなければならない。
    private final StockListService stockListService;

    public StockListRestController(StockListService stockListService) {
        this.stockListService = stockListService;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody StockListForm form) {
        stockListService.insertStockInfo(form);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody StockListForm form) {
        try {
            stockListService.updateStockInfo(form);
            return ResponseEntity.ok("更新に成功しました");
        } catch (Exception e) {
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

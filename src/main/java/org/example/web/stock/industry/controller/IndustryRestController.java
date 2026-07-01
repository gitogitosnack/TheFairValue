package org.example.web.stock.industry.controller;

import org.example.web.stock.industry.domain.IndustryForm;
import org.example.web.stock.industry.service.IndustryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest_industries")
public class IndustryRestController {

    private final IndustryService industryService;

    public IndustryRestController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody IndustryForm form) {
        industryService.insertIndustryInfo(form);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody IndustryForm form) {
        try {
            industryService.updateIndustryInfo(form);
            return ResponseEntity.ok("更新に成功しました");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            industryService.deleteIndustryInfoById(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("削除に失敗しました");
        }
    }
}

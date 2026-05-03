package org.example.web.master.valuationmodel.controller;

import org.example.web.master.valuationmodel.domain.ValuationModelResponseDto;
import org.example.web.master.valuationmodel.service.ValuationModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/valuation-models")
public class ValuationModelController {

    private final ValuationModelService valuationModelService;

    public ValuationModelController(ValuationModelService valuationModelService) {
        this.valuationModelService = valuationModelService;
    }

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("valuation-model-list/valuation-model-list");
        List<ValuationModelResponseDto> models = valuationModelService.initialDispAll();
        mav.addObject("items", models);
        return mav;
    }
}

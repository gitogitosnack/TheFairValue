package org.example.web.master.industry.controller;

import org.example.web.master.industry.domain.IndustryResponseDto;
import org.example.web.master.industry.service.IndustryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/industries")
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping("")
    public ModelAndView display(ModelAndView mav) {
        mav.setViewName("industry-list/industry-list");
        List<IndustryResponseDto> industries = industryService.initialDispAll();
        mav.addObject("items", industries);
        return mav;
    }
}

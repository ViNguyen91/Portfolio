package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CriteriumController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping({"/criteria"})
    protected String showCriteria(Model model){
        model.addAttribute("allCriteria", criteriumRepository.findAll());

        return "criteriumOverview";
    }

    @GetMapping("/criteria/add")
    protected String showCriteriaForm(Model model){
        model.addAttribute("criterium", new Criterium());
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "criteriumForm";
    }

    @PostMapping({"/criteria/add"})
    protected String saveOrUpdateCriterium(@ModelAttribute("criterium") Criterium criterium, BindingResult result){
        if (result.hasErrors()) {
            return "criteriumForm";
        } else {
            criteriumRepository.save(criterium);
            return "redirect:/criteria";
        }
    }

}

package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CriteriumController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/criteria/{categoryId}")
    protected String showCriteria(@PathVariable("categoryId") final Integer categoryId, Model model){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("criteriaByCategory", category.get().getCriteria());
            model.addAttribute("category", category.get());
            return "criteriumOverview";
        } else {
            return "redirect:/category";
        }
    }

    @GetMapping("/criteria/add")
    protected String showCriteriaForm(Model model){
        model.addAttribute("criterium", new Criterium());
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "criteriumForm";
    }

    @GetMapping("/criteria/{categoryId}/add")
    protected String showCriteriaForm2(@PathVariable("categoryId") final Integer categoryId, Model model){
        model.addAttribute("criterium", new Criterium());
        Optional<Category> category = categoryRepository.findById(categoryId);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category.get());
        model.addAttribute("categoryList", categoryList);
        return "criteriumForm";
    }

    @PostMapping({"/criteria/add"})
    protected String saveOrUpdateCriterium(@ModelAttribute("criterium") Criterium criterium, BindingResult result){
        if (result.hasErrors()) {
            return "criteriumForm";
        } else {
            criteriumRepository.save(criterium);
            return "redirect:/criteria/" + criterium.getCategory().getCategoryId();
        }
    }

    @GetMapping("/criteria/delete/{criteriumId}")
    protected String deleteCriterium(@PathVariable("criteriumId") final Integer criteriumId) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            criteriumRepository.deleteById(criteriumId);
            return "forward:/criteria/" + criterium.get().getCategory().getCategoryId();
        }
        return "forward:/category";
    }

    @GetMapping("/criteria/{criteriumId}/overview")
    protected String showProcess(@PathVariable("criteriumId") final Integer criteriumId, Model model){
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            model.addAttribute("criterium", criterium.get());
            model.addAttribute("criteriumId", criteriumId);
            return "processOverview";
        } else {
            return "redirect:/criteria/" + criterium.get().getCategory().getCategoryId();
        }
    }
}

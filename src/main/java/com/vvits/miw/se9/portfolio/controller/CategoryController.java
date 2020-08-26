package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping({"/", "/category"})
    protected String showCategories(Model model){
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "categoryOverview";
    }

    @GetMapping("/category/add")
    protected String showCategoriesForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @PostMapping("/category/add")
    protected String saveOrUpdateCategory(@ModelAttribute("category") Category category, BindingResult result){
        if (result.hasErrors()){
            return "categoryForm";
        } else {
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }
}

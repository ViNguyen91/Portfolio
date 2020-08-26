package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

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

    @PostMapping("/category/add")
    protected String saveOrUpdateCategory(@ModelAttribute("category") Category category, BindingResult result){
        if (result.hasErrors()){
            return "categoryOverview";
        } else {
            categoryRepository.save(category);
            return "redirect:/category";
        }
    }

    @GetMapping("/category/{categoryId}")
    protected String showCategory(@PathVariable("categoryId") final Integer categoryId, BindingResult result, Model model){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "criteriumOverview";
        } else {
            return "redirect:/category";
        }
    }

    @GetMapping("/category/delete/{categoryId}")
    protected String deleteCategory(@PathVariable("categoryId") final Integer categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return "redirect:/category";
        } else {
            return "redirect:/category";
        }
    }

    /*@GetMapping("/category/delete/{categoryId}")
    protected String deleteCategory(@ModelAttribute("category")Category category, BindingResult result){
        categoryRepository.deleteById(category.getCategoryId());
        return "redirect:/category";
    }*/
}

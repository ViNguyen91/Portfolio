package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping({"", "/category/"})
    protected String showCategories(Model model) {
        model.addAttribute("allCategories", categoryRepository.findAll());
        model.addAttribute("category", new Category());
        return "categoryOverview";
    }

    @PostMapping("/add")
    protected String saveOrUpdateCategory(@ModelAttribute("category") Category category, BindingResult result) {
        // you don't have sapperate page for Add category so its always overview.
        if (!result.hasErrors()) {
            categoryRepository.save(category);
        }
        return "redirect:/category";
    }

    // Get Category Details with all CriteriumOverview
    @GetMapping("/{id}")
    protected String showCategory(@PathVariable("id") final Integer id, Model model) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "criteriumOverview";
        } else {
            return "redirect:/category";
        }
    }

    @GetMapping("/{id}/addCriterium")
    protected String ShowAddCriterium(@PathVariable("id") final Integer id, Model model) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Criterium criterium = new Criterium();
            criterium.setCategory(category.get());
            List<Category> categoryList = new ArrayList<>();
            categoryList.add(category.get());
            model.addAttribute("criterium", criterium);
            model.addAttribute("categoryList", categoryList);
            return "criteriumForm";
        } else {
            return "redirect:/category";
        }
    }

    @GetMapping("/delete/{id}")
    protected String deleteCategory(@PathVariable("id") final Integer categoryId) {
        delete(categoryId);
        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    protected String postDeleteCategory(@PathVariable("id") final Integer categoryId) {
        delete(categoryId);
        return "redirect:/category";
    }

    private void delete(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

}

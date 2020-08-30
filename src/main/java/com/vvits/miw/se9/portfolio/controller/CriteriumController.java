package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.CategoryRepository;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("criterium") //dont use dont use different name than the class its hard to maintains
public class CriteriumController {

    final CriteriumRepository criteriumRepository;

    final CategoryRepository categoryRepository;

    public CriteriumController(CriteriumRepository criteriumRepository, CategoryRepository categoryRepository) {
        this.criteriumRepository = criteriumRepository;
        this.categoryRepository = categoryRepository;
    }

    // Show Details of criterium. Here you can edit or delete Stuff.
    @GetMapping("/{id}")
    protected String showCriteria(@PathVariable("id") final Integer id, Model model) {
        Optional<Criterium>criteriumOptional = criteriumRepository.findById(id);
        if (criteriumOptional.isPresent()) {
            model.addAttribute("criterium", criteriumOptional.get());
            List<Category> categoryList = new ArrayList<>();
            categoryList.add(criteriumOptional.get().getCategory());
            model.addAttribute("categoryList", categoryList);
            return "criteriumForm";
        } else {
            return "redirect:/category";
        }
    }

    @PostMapping("/add")
    protected String create(@ModelAttribute("criterium") Criterium criterium, BindingResult result) {
        if (result.hasErrors()) {
            return "criteriumForm";
        } else {
            criteriumRepository.save(criterium);
            return "redirect:/category/" + criterium.getCategory().getId();
        }
    }

    @GetMapping("/{id}/delete")
    protected String deleteCriterium(@PathVariable("id") final Integer criteriumId) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            criteriumRepository.delete(criterium.get());
        }
        return "redirect:/category/" + criterium.get().getCategory().getId();
    }

    @DeleteMapping("/{id}")
    protected String deleteCriteriumPos(@PathVariable("id") final Integer criteriumId) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            criteriumRepository.deleteById(criteriumId);
            return "redirect:/criteria/" + criterium.get().getCategory().getId();
        }
        return "redirect:/category";
    }

    private void deleteCriterium2(Integer id) {
        Optional<Criterium> criterium = criteriumRepository.findById(id);
        if (criterium.isPresent()) {
            criteriumRepository.delete(criterium.get());
        }

    }

    @GetMapping("/{id}/overview")
    protected String showProcess(@PathVariable("id") final Integer criteriumId, Model model) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            model.addAttribute("criterium", criterium.get());
            model.addAttribute("criteriumId", criteriumId);
            return "processOverview";
        } else {
            return "redirect:/criteria/" + criterium.get().getCategory().getId();
        }
    }
}

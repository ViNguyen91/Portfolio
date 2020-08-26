package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import com.vvits.miw.se9.portfolio.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TargetController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    TargetRepository targetRepository;

    @GetMapping("/target/add")
    protected String showTargetForm(Model model){
        model.addAttribute("target", new Target());
        model.addAttribute("targetList", targetRepository.findAll());
        return "targetForm";
    }

    @PostMapping("/target/add")
    protected String saveOrUpdateTarget(BindingResult result, @ModelAttribute("target") Target target) {
        if (result.hasErrors()) {
            return "targetForm";
        } else {
            targetRepository.save(target);
            return "redirect:/criteria/" + target.getCriterium().getCriteriumId();
        }
    }


}

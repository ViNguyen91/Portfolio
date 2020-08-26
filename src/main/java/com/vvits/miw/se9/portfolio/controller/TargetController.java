package com.vvits.miw.se9.portfolio.controller;

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

    @GetMapping("/target/add/{criteriumId}")
    protected String showTagerForm(@PathVariable("criteriumId") final Integer criteriumId, Model model) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            Criterium c = criterium.get();
            Target target = new Target();
            target.setCriterium(c);
            model.addAttribute("target", target);
            model.addAttribute("criteriumId", criteriumId);
            return "targetForm";
        } else {
            return "redirect:/category";
        }
    }


    @PostMapping("/target/add/{criteriumId}")
    protected String saveOrUpdateReview(@PathVariable("criteriumId") final Integer criteriumId, @ModelAttribute("target") Target target, BindingResult result) {
        if (result.hasErrors()) {
//            return "redirect:/category";
            return "targetForm";
        } else {
            Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
            if (criterium.isPresent()) {
                target.setCriterium(criterium.get());
                targetRepository.save(target);
            }
            return "redirect:/criteria/" + criteriumId;
        }
    }

}

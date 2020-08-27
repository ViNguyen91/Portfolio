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

    @GetMapping("/target/{criteriumId}")
    protected String showTargets(@PathVariable("criteriumId") final Integer criteriumId, Model model){
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            model.addAttribute("targetsByCriterium", criterium.get().getTargets());
            model.addAttribute("criteriumId", criteriumId);
            return "targetOverview";
        } else {
            return "redirect:/criteria/{categoryId}";
        }
    }

    @GetMapping("/target/add/{criteriumId}")
    protected String showTargetForm(@PathVariable("criteriumId") final Integer criteriumId, Model model){
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if(criterium.isPresent()){
            Target target = new Target();
            target.setCriterium(criterium.get());
            model.addAttribute("target", target);
            model.addAttribute("criteriumId", criteriumId);
            return "targetForm";
        }else {
            return "redirect:/criteria/{categoryId}";
        }
    }

    @PostMapping("/target/add/{criteriumId}")
    protected String saveOrUpdateTarget(@PathVariable("criteriumId") final Integer criteriumId,
                                        @ModelAttribute("target") Target target, BindingResult result) {
        if (result.hasErrors()) {
            return "targetForm";
        } else {
            Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
            if(criterium.isPresent()) {
                target.setCriterium(criterium.get());
                targetRepository.save(target);
            }
            return "redirect:/category";
        }
    }

    @GetMapping("/target/delete/{targetId}")
    protected String deleteTarget(@PathVariable("targetId") final Integer targetId) {
        Optional<Target> target = targetRepository.findById(targetId);
        if (target.isPresent()) {
            targetRepository.deleteById(targetId);
            return "targetOverview";
        }
        return "forward:/criteria/" + target.get().getCriterium().getCriteriumId();
    }

}

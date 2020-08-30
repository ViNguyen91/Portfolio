package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import com.vvits.miw.se9.portfolio.repository.TargetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("target")
public class TargetController {

    final CriteriumRepository criteriumRepository;

    final TargetRepository targetRepository;

    public TargetController(CriteriumRepository criteriumRepository, TargetRepository targetRepository) {
        this.criteriumRepository = criteriumRepository;
        this.targetRepository = targetRepository;
    }

    // Target Overview
    @GetMapping("/{id}")
    protected String showTargets(@PathVariable("id") final Integer id, Model model){
        Optional<Target> targetOptional = targetRepository.findById(id);
        if (targetOptional.isPresent()) {
            model.addAttribute("target", targetOptional.get());
            return "targetForm";
        }
        return "redirect:/category/";
    }

    @PostMapping("/add")
    protected String saveOrUpdateTarget(@ModelAttribute("target") Target target, BindingResult result) {
        if (result.hasErrors()) {
            return "targetForm";
        } else {
            targetRepository.save(target);
            return "redirect:/criterium/"+target.getCriterium().getId()+"/overview";
        }
    }

    @GetMapping("/{targetId}/delete")
    protected String deleteTarget(@PathVariable("targetId") final Integer targetId) {
        Optional<Target> target = targetRepository.findById(targetId);
        int theId = target.get().getCriterium().getId();
        if (target.isPresent()) {
            targetRepository.deleteById(targetId);
            return "redirect:/criterium/"+target.get().getCriterium().getId()+"/overview";
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    protected String deleteTargetPos(@PathVariable("id") final Integer targetId) {
        return deleteTarget(targetId);
    }
}

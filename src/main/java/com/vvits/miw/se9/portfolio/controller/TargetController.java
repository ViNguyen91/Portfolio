package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import com.vvits.miw.se9.portfolio.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class TargetController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    TargetRepository targetRepository;

    @GetMapping("/target/add/{criteriumId}")
    protected String addTarget(@PathVariable("criteriumId") final Integer criteriumId) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()){
            Target target = new Target();
            target.setCriterium(criterium.get());
            targetRepository.save(target);
        }
        return "redirect:/criteria";
    }
}

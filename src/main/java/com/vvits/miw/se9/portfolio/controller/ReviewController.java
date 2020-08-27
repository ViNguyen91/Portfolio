package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Review;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.CriteriumRepository;
import com.vvits.miw.se9.portfolio.repository.ReviewRepository;
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
public class ReviewController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/review/add/{criteriumId}")
    protected String showReviewForm(@PathVariable("criteriumId") final Integer criteriumId, Model model) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()){
            Review review = new Review();
            review.setCriterium(criterium.get());
            model.addAttribute("review", review);
            return "reviewForm";
        }else {
            return "redirect:/category";
        }
    }

    @PostMapping({"/review/add"})
    protected String saveOrUpdateReview(@ModelAttribute("review") Review review, BindingResult result){
        if (result.hasErrors()) {
            return "reviewForm";
        } else {
            reviewRepository.save(review);
            return "redirect:/criteria";
        }
    }
}

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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    CriteriumRepository criteriumRepository;

    @Autowired
    ReviewRepository reviewRepository;

    // Get Detail Page
    @GetMapping("/{id}")
    protected String showReviews(@PathVariable("id") final Integer reviewId, Model model) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            model.addAttribute("review", reviewOptional.get());
            return "targetForm.html";
        }
        return "redirect:/";
    }

    @PostMapping("/add")
    protected String saveOrUpdateReview(@ModelAttribute("review") Review review, BindingResult result) {
        if (result.hasErrors()) {
            return "reviewForm";
        } else {
            saveOrUpdateReview(review);
            return "redirect:/criterium/"+review.getCriterium().getId()+"/overview";
        }
    }

    private boolean saveOrUpdateReview(Review review) {
        if(review.getCriterium()==null || review.getCriterium().getId() == null) return false;

        Integer criteriumId = review.getCriterium().getId();
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            reviewRepository.save(review);
            return true;
        }
        return  false;
    }

    @GetMapping("/{id}/delete")
    protected String deleteReview(@PathVariable("id") final Integer reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return "redirect:/criterium/"+review.get().getCriterium().getId()+"/overview";
        }
        return "redirect:/";

    }
}

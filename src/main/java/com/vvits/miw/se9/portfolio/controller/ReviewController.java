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

    @GetMapping("/review/{criteriumId}")
    protected String showReviews(@PathVariable("criteriumId") final Integer criteriumId, Model model){
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            model.addAttribute("reviewsByCriterium", criterium.get().getReviews());
            model.addAttribute("criteriumId", criteriumId);
            return "processOverview";
        } else {
            return "redirect:/criteria/" + criterium.get().getCategory().getCategoryId();
        }
    }

    @GetMapping("/review/add/{criteriumId}")
    protected String showReviewForm(@PathVariable("criteriumId") final Integer criteriumId, Model model) {
        Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
        if (criterium.isPresent()) {
            Review review = new Review();
            review.setCriterium(criterium.get());
            model.addAttribute("review", review);
            model.addAttribute("criteriumId", criteriumId);
            model.addAttribute("targetsByCriterium", criterium.get().getTargets());
            return "reviewForm";
        }
            return "redirect:/criteria/{categoryId}";
    }

    @PostMapping("/review/add/{criteriumId}")
    protected String saveOrUpdateReview(@PathVariable("criteriumId") final Integer criteriumId,
                                        @ModelAttribute("review") Review review, BindingResult result){
        if (result.hasErrors()) {
            return "reviewForm";
        } else {
            Optional<Criterium> criterium = criteriumRepository.findById(criteriumId);
            if (criterium.isPresent()) {
                review.setCriterium(criterium.get());
                reviewRepository.save(review);
                if (review.getTarget() == null) {
                    Target target = new Target();
                    review.setTarget(target);
                    target.setDescription(review.getDescription());
                    target.setCriterium(review.getCriterium());
                }
            }
            return "redirect:/review/" + criteriumId;
        }
    }

    @GetMapping("/review/delete/{reviewId}")
    protected String deleteReview(@PathVariable("reviewId") final Integer reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        int theId = review.get().getCriterium().getCriteriumId();
        if (review.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return "redirect:/review/" + theId;
        }
        return "redirect:/review/" + theId;
    }
}

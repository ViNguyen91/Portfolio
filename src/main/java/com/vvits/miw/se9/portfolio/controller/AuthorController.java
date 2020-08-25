package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Category;
import com.vvits.miw.se9.portfolio.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/author")
    protected String showAuthors(Model model){
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("author", new Category());
        return "authorOverview";
    }

    @PostMapping("/author/new")
    protected String saveOrUpdateAuthor(@ModelAttribute("author") Category category, BindingResult result){
        if (result.hasErrors()){
            return "authorOverview";
        } else {
            authorRepository.save(category);
            return "redirect:/author";
        }
    }
}

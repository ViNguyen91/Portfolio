package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.repository.AuthorRepository;
import com.vvits.miw.se9.portfolio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BooksController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping({"/", "/books"})
    protected String showBooks(Model model){
        model.addAttribute("allBooks", bookRepository.findAll());
        return "bookOverview";
    }

    @GetMapping("/books/add")
    protected String showBookForm(Model model){
        model.addAttribute("book", new Criterium());
        model.addAttribute("authorList", authorRepository.findAll());
        return "bookForm";
    }

    @GetMapping("/books/update")
    protected String showBookUpdateForm(Model model){
        Criterium criterium = bookRepository.findByTitle("hoi").orElse(new Criterium());
        model.addAttribute("book", criterium);
        model.addAttribute("authorList", authorRepository.findAll());
        return "bookForm";
    }

    @PostMapping({"/books/add"})
    protected String saveOrUpdateBook(@ModelAttribute("book") Criterium criterium, BindingResult result){
        if (result.hasErrors()) {
            return "bookForm";
        } else {
            bookRepository.save(criterium);
            return "redirect:/books";
        }
    }
}

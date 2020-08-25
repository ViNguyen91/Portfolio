package com.vvits.miw.se9.portfolio.controller;

import com.vvits.miw.se9.portfolio.model.Criterium;
import com.vvits.miw.se9.portfolio.model.Target;
import com.vvits.miw.se9.portfolio.repository.BookRepository;
import com.vvits.miw.se9.portfolio.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CopyController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CopyRepository copyRepository;

    @GetMapping("/copy/add/{bookId}")
    protected String addCopy(@PathVariable("bookId") final Integer bookId) {
        Optional<Criterium> book = bookRepository.findById(bookId);
        if (book.isPresent()){
            Target target = new Target();
            target.setBook(book.get());
            copyRepository.save(target);
        }
        return "redirect:/books";
    }
}

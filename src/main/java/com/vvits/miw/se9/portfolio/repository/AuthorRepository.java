package com.vvits.miw.se9.portfolio.repository;

import com.vvits.miw.se9.portfolio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Category, Integer> {
}

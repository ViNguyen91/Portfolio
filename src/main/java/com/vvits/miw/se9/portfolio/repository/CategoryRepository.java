package com.vvits.miw.se9.portfolio.repository;

import com.vvits.miw.se9.portfolio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

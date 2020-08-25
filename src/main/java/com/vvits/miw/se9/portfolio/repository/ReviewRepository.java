package com.vvits.miw.se9.portfolio.repository;

import com.vvits.miw.se9.portfolio.model.Review;
import com.vvits.miw.se9.portfolio.model.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

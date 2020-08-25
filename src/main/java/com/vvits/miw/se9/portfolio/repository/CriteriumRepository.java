package com.vvits.miw.se9.portfolio.repository;

import com.vvits.miw.se9.portfolio.model.Criterium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CriteriumRepository extends JpaRepository<Criterium, Integer> {
}

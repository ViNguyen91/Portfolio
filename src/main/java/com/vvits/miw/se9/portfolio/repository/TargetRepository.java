package com.vvits.miw.se9.portfolio.repository;

import com.vvits.miw.se9.portfolio.model.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetRepository extends JpaRepository<Target, Integer> {
    //Optional<Target> findByCriteriumId(int criteriumId);
}

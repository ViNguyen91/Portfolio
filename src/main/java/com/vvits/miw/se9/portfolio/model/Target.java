package com.vvits.miw.se9.portfolio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer targetId;

    @Column(columnDefinition = "text")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criteriumId", referencedColumnName = "criteriumId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Criterium criterium;

    @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "criterium")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Review review;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Criterium getCriterium() {
        return criterium;
    }

    public void setCriterium(Criterium criterium) {
        this.criterium = criterium;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}

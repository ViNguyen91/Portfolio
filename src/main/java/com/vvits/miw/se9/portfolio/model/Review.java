package com.vvits.miw.se9.portfolio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewId;

    private String description;

    private String priority;

    private String gitCommit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criteriumId", referencedColumnName = "criteriumId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Criterium criterium;

    @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "criterium")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Target target;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Criterium getCriterium() {
        return criterium;
    }

    public void setCriterium(Criterium criterium) {
        this.criterium = criterium;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getGitCommit() {
        return gitCommit;
    }

    public void setGitCommit(String gitCommit) {
        this.gitCommit = gitCommit;
    }
}

package com.vvits.miw.se9.portfolio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer targetId;

    private String description;
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criteriumId", referencedColumnName = "criteriumId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Criterium criterium;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

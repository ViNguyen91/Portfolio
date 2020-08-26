package com.vvits.miw.se9.portfolio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
public class Criterium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer criteriumId;

    private int points;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //lazy alleen de id
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToMany( cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                mappedBy = "criterium")
    private List<Target> targets;

    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "criterium")
    private List<Review> reviews;

    public Integer getCriteriumId() {
        return criteriumId;
    }

    public void setCriteriumId(Integer criteriumId) {
        this.criteriumId = criteriumId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

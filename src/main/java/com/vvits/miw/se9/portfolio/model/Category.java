package com.vvits.miw.se9.portfolio.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    private String name;

    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "category")
    private List<Criterium> criteria;

    public Category() {
    }

    public Category(Integer categoryId, String name, List<Criterium> criteria) {
        this.categoryId = categoryId;
        this.name = name;
        this.criteria = criteria;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Criterium> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criterium> criteria) {
        this.criteria = criteria;
    }
}

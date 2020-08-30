package com.vvits.miw.se9.portfolio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Comparable<Category> {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Criterium> criteria;

    @Override
    public int compareTo(Category o) {
        return getDescription().compareTo(o.getDescription());
    }
}

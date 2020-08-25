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
                mappedBy = "book")
    private List<Target> copies;

    public int getNoCopies(){
        return copies.size();
    }

    public Category getAuthor() {
        return category;
    }

    public void setAuthor(Category category) {
        this.category = category;
    }

    public Integer getCriteriumId() {
        return criteriumId;
    }

    public void setCriteriumId(Integer bookId) {
        this.criteriumId = bookId;
    }


}

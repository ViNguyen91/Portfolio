package com.vvits.miw.se9.portfolio.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Review extends BaseModel  {

    private String priority;

    private String gitCommit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criteriumId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Criterium criterium;

    @OneToOne
    @JoinColumn(name = "targetId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Target target;
}

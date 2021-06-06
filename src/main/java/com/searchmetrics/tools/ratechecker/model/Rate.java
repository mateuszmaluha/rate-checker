package com.searchmetrics.tools.ratechecker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Rate {
    public Rate(Date creationDate, Double value) {
        this.creationDate = creationDate;
        this.value = value;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private Double value;


}

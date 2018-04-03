package com.example.confeo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by mstobieniecka on 2018-04-03.
 */
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    @ManyToOne
    private Event event;
}

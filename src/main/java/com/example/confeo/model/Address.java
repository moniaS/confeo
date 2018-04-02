package com.example.confeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String streetName;
    private String streetNumber; //it could be "13/15", so that's why it's String instead of int
    private String cityName;
}

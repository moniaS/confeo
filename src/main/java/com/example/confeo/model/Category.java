package com.example.confeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

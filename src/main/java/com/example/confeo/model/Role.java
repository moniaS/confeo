package com.example.confeo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mstobieniecka on 2018-04-01.
 */
@Data
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private RoleName roleName;
}

package com.example.confeo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mstobieniecka on 2018-04-01.
 */
@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column
    private String email;
    @NotNull
    private String password;
    private String firstname;
    private String lastname;
    private Role role;
    @ManyToMany(mappedBy = "users")
    private Set<Event> events = new HashSet<>();
    @OneToMany
    private Set<Prelection> prelections = new HashSet<>();
    @OneToMany
    private Set<Payment> payments = new HashSet<>();
}

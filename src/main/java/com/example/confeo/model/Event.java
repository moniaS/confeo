package com.example.confeo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mstobieniecka on 2018-04-01.
 */
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    private Address address;
    private String name;
    private String description;
    private EventStatus status;
    private EventType type;
    private Category category;
    private boolean isFree;
    private double pricePerParticipant;
    private double pricePerPrelegent;
    @OneToMany(mappedBy = "event")
    Set<Prelection> prelections = new HashSet<>();
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> users = new HashSet<>();
}

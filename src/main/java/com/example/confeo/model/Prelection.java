package com.example.confeo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by mstobieniecka on 2018-04-01.
 */
@Data
@Entity
public class Prelection {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private PrelectionStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}

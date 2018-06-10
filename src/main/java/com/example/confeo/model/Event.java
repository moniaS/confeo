package com.example.confeo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
    @Enumerated(EnumType.STRING)
    private EventType type;
    private Boolean isFree;
    private double pricePerParticipant;
    private double pricePerPrelegent;
    private Integer maxParticipants;
    @ManyToOne(cascade = CascadeType.ALL)
    Category category;
    @OneToMany(mappedBy = "event")
    private Set<Prelection> prelections = new HashSet<>();
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();
    
    @ManyToOne
    User organiser;

/*    @Transient
    private boolean validDateRange;
    @AssertTrue(message = "Data rozpoczęnia musi być przed datą zakończenia")
    public boolean isValidDateRange() {
    	if (endDate == null || startDate ==null){
    		return false;
    	}
    	return endDate.compareTo(startDate) >= 0;
    }*/
//    @OneToMany
//    private Set<Payment> receivedPayments = new HashSet<>();
//    @OneToMany
//    private Set<Payment> madePayments = new HashSet<>();
}

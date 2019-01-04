package com.example.confeo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by mstobieniecka on 2018-04-01.
 */
@Data
@Entity
@NoArgsConstructor
@ToString(exclude={"prelections","attendingEvents","organizedEvents"})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Column
    @SafeHtml
    private String email;
    @NotNull
    @SafeHtml
    private String password;
    @SafeHtml
    private String firstname;
    @SafeHtml
    private String lastname;
    private Boolean areTermsAccepted;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(mappedBy = "users")
    private Set<Event> attendingEvents = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private List<Prelection> prelections = new ArrayList<>();
    @OneToMany(mappedBy = "organiser")
    private List<Event> organizedEvents = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    @OneToMany
//    private Set<Payment> receivedPayments = new HashSet<>();
//    @OneToMany
//    private Set<Payment> madePayments = new HashSet<>();
}

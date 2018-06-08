package com.example.confeo.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
public enum Role implements GrantedAuthority {
    ROLE_PARTICIPANT, ROLE_ADMIN, ROLE_ORGANIZER;

    @Override
    public String getAuthority() {
        return name();
    }
}

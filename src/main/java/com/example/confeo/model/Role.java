package com.example.confeo.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
public enum Role implements GrantedAuthority {
    ROLE_PARTICIPANT("Uczestnik"), ROLE_ADMIN("Admin"), ROLE_ORGANIZER("Organizator");

    private final String text;

    Role(final String text) {
        this.text = text;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return text;
    }
}

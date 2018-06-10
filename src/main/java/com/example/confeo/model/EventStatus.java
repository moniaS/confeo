package com.example.confeo.model;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
public enum EventStatus {
    UPCOMING("Nadchodzące"),
    FINISHED("Zakończone"),
    CANCELED("Anulowane");

    private final String text;

    EventStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

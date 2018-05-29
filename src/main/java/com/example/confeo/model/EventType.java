package com.example.confeo.model;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
public enum EventType {
    CONFERENCE("Konferencja"),
    WORKSHOPS("Warsztaty"),
    LECTURE("Wykład"),
    TRAINING("Szkolenie"),
    OTHER("Inne");

    private final String text;

    EventType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

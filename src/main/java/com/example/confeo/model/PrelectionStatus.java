package com.example.confeo.model;

/**
 * Created by mstobieniecka on 2018-04-02.
 */
public enum PrelectionStatus {
    WAITING("Oczekująca"),
    SUBMITTED("Zatwierdzona"),
    CANCELED("Anulowana"),
    REJECTED("Odrzucona");

    private final String text;

    PrelectionStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

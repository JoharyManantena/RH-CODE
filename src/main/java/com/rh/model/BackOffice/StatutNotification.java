package com.rh.model.BackOffice;

public enum StatutNotification {
    NON_LUE("Non_lue"),
    LUE("Lue");

    private final String label;

    StatutNotification(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package com.adva.project.model;

public enum TypeOfTransport {

    BENZINE_CAR("Samochód benzynowy"),DIESEL_CAR("Samochód z silnikiem diesla"),
    RACING_MOTORCYCLE("Motocykl do wyścigów"), CRUISING_MOTORCYCLE("Motocykl na rejs"),
    CROSS_COUNTRY_MOTORCYCLE("Motocykl crossowy");

    String translation;

    public String getTranslation() {
        return translation;
    }

    TypeOfTransport(String translation) {
        this.translation = translation;
    }


}

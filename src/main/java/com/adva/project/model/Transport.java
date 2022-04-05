package com.adva.project.model;

public interface Transport extends Announcement {
    void setBrand(String brand);
    String getBrand();
    void setModel(String model);
    String getModel();
    void setMileage(Double mileage);
    Double getMileage();
}

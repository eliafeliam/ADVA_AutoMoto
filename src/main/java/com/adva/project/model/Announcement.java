package com.adva.project.model;

public interface Announcement{
    void setId(int id);
    int getId();
    void setTitle(String title);
    String getTitle();
    void setDescription(String description);
    String getDescription();
    void setPrice(int price);
    int getPrice();
    void setPhoneNumber(String phoneNumber);
    String getPhoneNumber();
    void setEmail(String email);
    String getEmail();
}
package com.adva.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@Table(name = "announcement")
public class TransportImpl implements Transport{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Tytuł nie może być pusty")
    @Size(min = 10, max = 25, message =
            "Tytuł ogłoszenia musi mieć co najmniej 10 i nie więcej niż 25 znaków")
    String title;

    @Enumerated(value = EnumType.STRING)
    private TypeOfTransport type;

    @NotEmpty(message = "Marka nie może być pusta")
    @Size(min = 2, max = 30, message = "Marka musi mieć co najmniej 2 i nie więcej niż 30 znaków")
    private String brand;

    @NotEmpty(message = "Model nie może być pusta")
    @Size(min = 2, max = 30, message = "Model musi mieć co najmniej 2 i nie więcej niż 30 znaków")
    private String model;

    @Max(value = 2500000, message = "Nie może być więcej niż 2.500.000 znaków")
    private Double mileage;

    @NotEmpty(message = "Napisz opis")
    @Size(min = 20, max = 500, message = "Opis musi mieć co najmniej 20 i nie więcej niż 500 znaków")
    protected String description;

    @Min(value = 1, message = "Nie może być mniejsza niż 1 znak")
    protected int price;

    @Size(min = 9, max = 9, message = "Telefon musi mieć 9 znaków")
    protected String phoneNumber;

    @NotEmpty(message = "Email należy wypełnić")
    @Email(message = "Niepoprawny Email")
    protected String email;


    public TransportImpl() {
    }
}

package com.rentcar.rentcar.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int manufactureYear;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    public enum FuelType {
        ELECTRIC, BENZINE, DIESEL
    }

    public enum BodyType {
        SEDAN, SUV, CONVERTIBLE
    }
}




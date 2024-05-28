package com.rentcar.rentcar.dto;

import com.rentcar.rentcar.model.CarEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CarDTO {

    @Nullable
    private Long id;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotNull
    private Integer manufactureYear;

    @NotNull
    private CarEntity.FuelType fuelType;

    @NotNull
    private CarEntity.BodyType bodyType;

    @Nullable
    private Boolean isRented;
}

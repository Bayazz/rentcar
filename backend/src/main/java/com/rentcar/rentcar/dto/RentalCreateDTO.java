package com.rentcar.rentcar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RentalCreateDTO {

    @NotNull
    Long customerId;

    @NotNull
    Long carId;

    @NotNull
    Long startDate;

    @NotNull
    Long endDate;

    @NotNull
    Integer kilometersDriven;
}

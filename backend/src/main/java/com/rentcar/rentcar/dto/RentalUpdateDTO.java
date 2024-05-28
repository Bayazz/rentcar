package com.rentcar.rentcar.dto;

import jakarta.annotation.Nullable;
import lombok.Value;


@Value
public class RentalUpdateDTO {

    @Nullable
    Long startDate;

    @Nullable
    Long endDate;

    @Nullable
    Integer kilometersDriven;
}

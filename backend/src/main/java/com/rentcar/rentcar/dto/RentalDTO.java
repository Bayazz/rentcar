package com.rentcar.rentcar.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalDTO {

    @Hidden
    @Nullable
    private Long id;

    @NotBlank
    private String customerName;

    @NotBlank
    private String carModel;

    @NotNull
    private Long startDate;

    @NotNull
    private Long endDate;

    @NotNull
    private Integer kilometersDriven;
}

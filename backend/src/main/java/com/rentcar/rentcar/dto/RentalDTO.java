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

    @Schema(description = "Name of a customer", example = "1")
    @NotBlank
    private String customerName;

    @Schema(description = "Model of a car", example = "1")
    @NotBlank
    private String carModel;

    @Schema(description = "Start date of the rental in UTC milliseconds", example = "1716654646097")
    @NotNull
    private Long startDate;

    @Schema(description = "End date of the rental in UTC milliseconds", example = "1717756646097")
    @NotNull
    private Long endDate;

    @Schema(description = "Total kilometers driven during the rental period", example = "500")
    @NotNull
    private Integer kilometersDriven;
}

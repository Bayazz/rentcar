package com.rentcar.rentcar.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CustomerDTO {

    @Nullable
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}

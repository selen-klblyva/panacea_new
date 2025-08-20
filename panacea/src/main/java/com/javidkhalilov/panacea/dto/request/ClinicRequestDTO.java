package com.javidkhalilov.panacea.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ClinicRequestDTO(
        @NotBlank(message = "Clinic name cannot be blank")
        @Size(max = 30, message = "Clinic name cannot exceed 30 characters")
        String name,

        @Size(max = 50, message = "Address cannot exceed 50 characters")
        String address,

        @Size(max = 12, message = "Contact number cannot exceed 12 characters")
        String contactNumber) {
}

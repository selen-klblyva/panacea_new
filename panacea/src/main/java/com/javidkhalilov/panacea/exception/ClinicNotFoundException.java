package com.javidkhalilov.panacea.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClinicNotFoundException extends RuntimeException {
    final Long clinicId;

    public ClinicNotFoundException(Long clinicId) {
        super("Clinic not found with id: " + clinicId);
        this.clinicId = clinicId;
    }
    public Long getClinicId() {
        return clinicId;
    }
}

package com.javidkhalilov.panacea.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@RequiredArgsConstructor
public class ClinicNotFoundException extends RuntimeException {
    private final Long clinicId;

    @Override
    public String getMessage() {
        return "Clinic not found with id: " + clinicId;
    }
}


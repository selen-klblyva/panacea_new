package com.javidkhalilov.panacea.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {
    SUCCESS("Success"),
    BAD_REQUEST("Request parameters invalid"),
    NOT_FOUND("Clinic not found"),
    VALIDATION_FAILED("Validation rules violated");

    private final String message;
}

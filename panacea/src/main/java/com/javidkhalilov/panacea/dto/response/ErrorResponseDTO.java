package com.javidkhalilov.panacea.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(
        Integer status,
        String error,
        String message,
        String errorDetail,
        String path,
        LocalDateTime timestamp) {
}

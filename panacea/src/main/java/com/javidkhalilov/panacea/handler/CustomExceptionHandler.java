package com.javidkhalilov.panacea.handler;

import com.javidkhalilov.panacea.dto.response.ErrorResponseDTO;
import com.javidkhalilov.panacea.enums.StatusEnum;
import com.javidkhalilov.panacea.exception.ClinicNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("No validation errors found");

        HttpStatus httpStatus = (status instanceof HttpStatus) ? (HttpStatus) status : HttpStatus.BAD_REQUEST;
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(StatusEnum.VALIDATION_FAILED.getMessage())
                .errorDetail(message)
                .path(uri)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(ClinicNotFoundException.class)
    protected ResponseEntity<ErrorResponseDTO> handleClinicNotFoundException(ClinicNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, StatusEnum.NOT_FOUND, request);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, StatusEnum.VALIDATION_FAILED, request);
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(Exception ex, HttpStatus status, StatusEnum statusEnum, HttpServletRequest request) {
        ErrorResponseDTO dto = errorDto(ex, status, statusEnum, request);
        return new ResponseEntity<>(dto, status);
    }

    private ErrorResponseDTO errorDto(Exception ex, HttpStatus status, StatusEnum statusEnum, HttpServletRequest servletRequest) {
        log.error("Exception occurred:", ex);
        return ErrorResponseDTO.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(statusEnum.getMessage())
                .errorDetail(ex.getMessage())
                .path(servletRequest.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

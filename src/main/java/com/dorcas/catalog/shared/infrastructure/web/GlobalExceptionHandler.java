package com.dorcas.catalog.shared.infrastructure.web;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;
import com.dorcas.catalog.shared.domain.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> notFound(NotFoundException e, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, e.getCode(), e.getMessage(), request, List.of());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorInfo> conflict(ConflictException e, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, e.getCode(), e.getMessage(), request, List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ErrorDetail> details = e.getBindingResult().getFieldErrors().stream()
                .map(f -> new ErrorDetail(f.getField(), f.getDefaultMessage()))
                .toList();
        return build(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_DATA,
                "The request contains invalid data", request, details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> unreadableJson(HttpMessageNotReadableException e, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_JSON,
                "The request body is not valid JSON or contains an unsupported value", request, List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorInfo> invalidParameter(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETER,
                "Parameter '" + e.getName() + "' does not accept the value '" + e.getValue() + "'", request, List.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_DATA, e.getMessage(), request, List.of());
    }

    private ResponseEntity<ErrorInfo> build(HttpStatus status, ErrorCode code, String message,
                                            HttpServletRequest request, List<ErrorDetail> details) {
        ErrorInfo error = new ErrorInfo(status.value(), status.getReasonPhrase(), code, message,
                request.getRequestURI(), Instant.now(), details);
        return ResponseEntity.status(status).body(error);
    }
}

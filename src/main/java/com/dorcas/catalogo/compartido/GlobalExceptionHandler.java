package com.dorcas.catalogo.compartido;

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

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorInfo> noEncontrado(RecursoNoEncontradoException e, HttpServletRequest request) {
        return construir(HttpStatus.NOT_FOUND, e.getCodigo(), e.getMessage(), request, List.of());
    }

    @ExceptionHandler({ConflictoException.class})
    public ResponseEntity<ErrorInfo> conflicto(ConflictoException e, HttpServletRequest request) {
        return construir(HttpStatus.CONFLICT, e.getCodigo(), e.getMessage(), request, List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> validacion(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ErrorDetalle> detalles = e.getBindingResult().getFieldErrors().stream()
                .map(f -> new ErrorDetalle(f.getField(), f.getDefaultMessage()))
                .toList();
        return construir(HttpStatus.BAD_REQUEST, "DATOS_NO_VALIDOS", "Hay datos no válidos en la petición", request, detalles);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> jsonIlegible(HttpMessageNotReadableException e, HttpServletRequest request) {
        return construir(HttpStatus.BAD_REQUEST, "JSON_NO_VALIDO",
                "El cuerpo de la petición no es un JSON válido o tiene un valor no admitido", request, List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorInfo> parametroIncorrecto(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        return construir(HttpStatus.BAD_REQUEST, "PARAMETRO_NO_VALIDO",
                "El parámetro '" + e.getName() + "' no admite el valor '" + e.getValue() + "'", request, List.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorInfo> argumentoIlegal(IllegalArgumentException e, HttpServletRequest request) {
        return construir(HttpStatus.BAD_REQUEST, "DATOS_NO_VALIDOS", e.getMessage(), request, List.of());
    }


    private ResponseEntity<ErrorInfo> construir(HttpStatus status, String codigo, String mensaje,
                                                HttpServletRequest request, List<ErrorDetalle> detalles) {
        ErrorInfo error = new ErrorInfo(status.value(), status.getReasonPhrase(), codigo, mensaje,
                request.getRequestURI(), Instant.now(), detalles);

        return ResponseEntity.status(status).body(error);
    }

}

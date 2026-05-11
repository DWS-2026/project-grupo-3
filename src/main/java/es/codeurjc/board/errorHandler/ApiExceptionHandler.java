package es.codeurjc.board.errorHandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import es.codeurjc.board.rest.dto.ApiErrorDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorDTO> handleNotFound(NoSuchElementException ex, HttpServletRequest request) {
        logger.warn("Recurso no encontrado: {} - {}", request.getRequestURI(), ex.getMessage());

        ApiErrorDTO error = new ApiErrorDTO(404, "NOT_FOUND", "Recurso no encontrado", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
        logger.warn("Endpoint no encontrado: {}", request.getRequestURI());

        ApiErrorDTO error = new ApiErrorDTO(404, "NOT_FOUND", "Endpoint no encontrado", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
        
    @ExceptionHandler({
        AuthenticationException.class,
        AuthenticationCredentialsNotFoundException.class,
        BadCredentialsException.class,
        InsufficientAuthenticationException.class
    })
    public ResponseEntity<ApiErrorDTO> handleUnauthorized(Exception ex, HttpServletRequest request) {
        logger.warn("Error de autenticación en {}: {} ({})", 
            request.getRequestURI(), ex.getMessage(), ex.getClass().getSimpleName());

        ApiErrorDTO error = new ApiErrorDTO(401, "UNAUTHORIZED", 
            "No autenticado. Credenciales inválidas o sesión expirada.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorDTO> handleForbidden(AccessDeniedException ex, HttpServletRequest request) {
        logger.warn("Acceso denegado en {}: {} ({})", 
            request.getRequestURI(), ex.getMessage(), ex.getClass().getSimpleName());

        ApiErrorDTO error = new ApiErrorDTO(403, "FORBIDDEN", 
            "Acceso denegado. No tienes permisos para acceder a este recurso.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleAllApiExceptions(Exception ex, HttpServletRequest request) {
        logger.error("Error en API {}: {} ({})", 
            request.getRequestURI(), ex.getMessage(), ex.getClass().getSimpleName());

        ApiErrorDTO error = new ApiErrorDTO(500, "INTERNAL_SERVER_ERROR", 
            "Error interno del servidor", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
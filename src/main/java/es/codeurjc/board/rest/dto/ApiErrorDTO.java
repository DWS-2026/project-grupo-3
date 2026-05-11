package es.codeurjc.board.rest.dto;

import java.time.LocalDateTime;

public record ApiErrorDTO(
    int status,
    String error,
    String message,
    String path,
    LocalDateTime timestamp
) {
    public ApiErrorDTO(int status, String error, String message, String path) {
        this(status, error, message, path, LocalDateTime.now());
    }
}

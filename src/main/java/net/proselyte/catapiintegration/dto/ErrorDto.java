package net.proselyte.catapiintegration.dto;

import java.time.LocalDateTime;

public record ErrorDto(String message,
                       String code,
                       LocalDateTime timestamp,
                       String path) {
    public ErrorDto(String message, String code, String path) {
        this(message, code, LocalDateTime.now(), path);
    }
}

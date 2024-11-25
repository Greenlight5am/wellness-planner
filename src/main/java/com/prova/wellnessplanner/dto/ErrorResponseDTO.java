package com.prova.wellnessplanner.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String message;
    private LocalDateTime timestamp;
    private String details;

    public ErrorResponseDTO(String message, String details) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }
}

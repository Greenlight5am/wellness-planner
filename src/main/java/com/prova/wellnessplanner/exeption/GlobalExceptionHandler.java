package com.prova.wellnessplanner.exeption;

import com.prova.wellnessplanner.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), "User not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleActivityNotFoundException(ActivityNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), "Activity not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalStateException(IllegalStateException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), "Invalid state");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), "Internal server error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

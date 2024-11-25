package com.prova.wellnessplanner.exeption;

/**
 * Eccezione personalizzata lanciata quando un utente non viene trovato.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}

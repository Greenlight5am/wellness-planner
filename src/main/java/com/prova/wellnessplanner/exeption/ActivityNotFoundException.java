package com.prova.wellnessplanner.exeption;

/**
 * Eccezione personalizzata lanciata quando un'attività non viene trovata.
 */
public class ActivityNotFoundException extends Exception {

    public ActivityNotFoundException(String message) {
        super(message);
    }
}

package com.prova.wellnessplanner.service;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.dto.UserDTO;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.exeption.UserNotFoundException;

import java.util.List;

/**
 * Interfaccia per il servizio User, definisce i metodi per gestire gli utenti.
 */
public interface UserService {

    /**
     * Registra un nuovo utente.
     *
     * @param userDTO Dati dell'utente da registrare.
     * @return UserDTO dell'utente registrato.
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Recupera un utente per ID.
     *
     * @param id ID dell'utente da recuperare.
     * @return UserDTO dell'utente trovato.
     * @throws UserNotFoundException Se l'utente non viene trovato.
     */
    UserDTO getUserById(Integer id) throws UserNotFoundException;

    /**
     * Iscrive un utente a un'attività.
     *
     * @param email     Email dell'utente.
     * @param activityId ID dell'attività.
     * @throws UserNotFoundException     Se l'utente non viene trovato.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    void subscribeToActivity(String email, Integer activityId) throws UserNotFoundException, ActivityNotFoundException;

    /**
     * Cancella l'iscrizione di un utente da un'attività.
     *
     * @param email     Email dell'utente.
     * @param activityId ID dell'attività.
     * @throws UserNotFoundException     Se l'utente non viene trovato.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    void unsubscribeFromActivity(String email, Integer activityId) throws UserNotFoundException, ActivityNotFoundException;

    /**
     * Recupera le attività a cui un utente è iscritto.
     *
     * @param email Email dell'utente.
     * @return Lista di ActivityDTO delle attività a cui l'utente è iscritto.
     * @throws UserNotFoundException Se l'utente non viene trovato.
     */
    List<ActivityDTO> getUserActivities(String email) throws UserNotFoundException;
}

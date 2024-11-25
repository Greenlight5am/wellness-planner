package com.prova.wellnessplanner.service;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaccia per il servizio Activity, definisce i metodi per gestire le attività.
 */
public interface ActivityService {

    /**
     * Crea una nuova attività.
     *
     * @param activityDTO Dati dell'attività da creare.
     * @return ActivityDTO dell'attività creata.
     */
    ActivityDTO createActivity(ActivityDTO activityDTO);

    /**
     * Aggiorna un'attività esistente.
     *
     * @param id          ID dell'attività da aggiornare.
     * @param activityDTO Dati aggiornati dell'attività.
     * @return ActivityDTO dell'attività aggiornata.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    ActivityDTO updateActivity(Integer id, ActivityDTO activityDTO) throws ActivityNotFoundException;

    /**
     * Elimina un'attività esistente.
     *
     * @param id ID dell'attività da eliminare.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    void deleteActivity(Integer id) throws ActivityNotFoundException;

    /**
     * Recupera un'attività per ID.
     *
     * @param id ID dell'attività da recuperare.
     * @return ActivityDTO dell'attività trovata.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    ActivityDTO getActivityById(Integer id) throws ActivityNotFoundException;

    /**
     * Recupera tutte le attività.
     *
     * @return Lista di ActivityDTO.
     */
    List<ActivityDTO> getAllActivities();

    /**
     * Cerca attività in base ai filtri forniti.
     *
     * @param nome          Nome dell'attività (opzionale).
     * @param giorno        Giorno dell'attività (opzionale).
     * @param disponibilita Disponibilità dell'attività (opzionale).
     * @return Lista di ActivityDTO che corrispondono ai criteri di ricerca.
     */
    List<ActivityDTO> searchActivities(String nome, LocalDate giorno, Boolean disponibilita);

}
package com.prova.wellnessplanner.controller;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.service.ActivityService;
import com.prova.wellnessplanner.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller REST per gestire le operazioni sulle attività.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    // Iniezione del servizio tramite campo privato final e costruttore
    private final ActivityService activityService;
    private final MediaService mediaService;

    public ActivityController(ActivityService activityService, MediaService mediaService) {
        this.activityService = activityService;
        this.mediaService = mediaService;
    }

    /**
     * Endpoint per creare una nuova attività.
     *
     * @param activityDTO Dati dell'attività da creare.
     * @return ResponseEntity contenente l'ActivityDTO creata.
     */
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        ActivityDTO createdActivity = activityService.createActivity(activityDTO);
        return ResponseEntity.ok(createdActivity);
    }

    /**
     * Endpoint per aggiornare un'attività esistente.
     *
     * @param id          ID dell'attività da aggiornare.
     * @param activityDTO Dati aggiornati dell'attività.
     * @return ResponseEntity contenente l'ActivityDTO aggiornata.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Integer id, @RequestBody ActivityDTO activityDTO) throws ActivityNotFoundException {
        ActivityDTO updatedActivity = activityService.updateActivity(id, activityDTO);
        return ResponseEntity.ok(updatedActivity);
    }

    /**
     * Endpoint per eliminare un'attività.
     *
     * @param id ID dell'attività da eliminare.
     * @return ResponseEntity con status appropriato.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) throws ActivityNotFoundException {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint per recuperare un'attività per ID.
     *
     * @param id ID dell'attività da recuperare.
     * @return ResponseEntity contenente l'ActivityDTO trovata.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Integer id) throws ActivityNotFoundException {
        ActivityDTO activityDTO = activityService.getActivityById(id);
        return ResponseEntity.ok(activityDTO);
    }

    /**
     * Endpoint per recuperare tutte le attività.
     *
     * @return ResponseEntity contenente la lista di ActivityDTO.
     */
    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

    /**
     * Endpoint per cercare attività in base ai filtri.
     *
     * @param nome          Nome dell'attività (opzionale).
     * @param giorno        Giorno dell'attività (opzionale).
     * @param disponibilita Disponibilità dell'attività (opzionale).
     * @return ResponseEntity contenente la lista di ActivityDTO che corrispondono ai criteri.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ActivityDTO>> searchActivities(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) LocalDate giorno,
            @RequestParam(required = false) Boolean disponibilita
            ) {

        List<ActivityDTO> activities = activityService.searchActivities(nome, giorno, disponibilita);
        return ResponseEntity.ok(activities);
    }

    /**
     * Endpoint per caricare un'immagine e associarla a un'attività esistente.
     *
     * @param id    ID dell'attività a cui associare l'immagine.
     * @param file  File dell'immagine da caricare.
     * @return ResponseEntity contenente un messaggio di conferma del caricamento e associazione.
     * @throws IOException              Se si verifica un errore durante la lettura del file.
     * @throws ActivityNotFoundException Se l'attività con l'ID specificato non esiste.
     */
    @PostMapping("/{id}/media")
    public ResponseEntity<String> uploadMediaForActivity(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) throws IOException, ActivityNotFoundException {

        mediaService.uploadMediaForActivity(id, file, activityService);
        return ResponseEntity.ok("L'immagine è stata caricata con successo e associata all'attività.");
    }
}

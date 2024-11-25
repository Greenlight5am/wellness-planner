package com.prova.wellnessplanner.controller;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.dto.UserDTO;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.exeption.UserNotFoundException;
import com.prova.wellnessplanner.mapper.UserMapper;
import com.prova.wellnessplanner.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per gestire le operazioni sugli utenti.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Iniezione del servizio tramite campo privato final e costruttore
    private final UserService userService;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
    }

    /**
     * Endpoint per registrare un nuovo utente.
     *
     * @param userDTO Dati dell'utente da registrare.
     * @return ResponseEntity contenente l'UserDTO registrato.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Endpoint per iscriversi a un'attività (richiede autenticazione).
     *
     * @param activityId     ID dell'attività a cui iscriversi.
     * @param authentication Dati dell'utente autenticato.
     * @return ResponseEntity con status appropriato.
     * @throws UserNotFoundException     Se l'utente non viene trovato.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    @PostMapping("/subscribe/{activityId}")
    public ResponseEntity<String> subscribeToActivity(@PathVariable Integer activityId, Authentication authentication) throws UserNotFoundException, ActivityNotFoundException {

        userService.subscribeToActivity(authentication.getName(), activityId);

        return ResponseEntity.ok("Iscrizione avvenuta con successo all'attività con ID " + activityId);
    }

    /**
     * Endpoint per cancellarsi da un'attività (richiede autenticazione).
     *
     * @param activityId     ID dell'attività da cui cancellarsi.
     * @param authentication Dati dell'utente autenticato.
     * @return ResponseEntity con status appropriato.
     * @throws UserNotFoundException     Se l'utente non viene trovato.
     * @throws ActivityNotFoundException Se l'attività non viene trovata.
     */
    @PostMapping("/unsubscribe/{activityId}")
    public ResponseEntity<String> unsubscribeFromActivity(@PathVariable Integer activityId, Authentication authentication) throws UserNotFoundException, ActivityNotFoundException {

        userService.unsubscribeFromActivity(authentication.getName(), activityId);
        return ResponseEntity.ok("Disicrizione avvenuta con successo all'attività con ID " + activityId);
    }

    /**
     * Endpoint per ottenere le attività a cui l'utente è iscritto (richiede autenticazione).
     *
     * @param authentication Dati dell'utente autenticato.
     * @return ResponseEntity contenente la lista di ActivityDTO.
     * @throws UserNotFoundException Se l'utente non viene trovato.
     */
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityDTO>> getUserActivities(Authentication authentication) throws UserNotFoundException {

        List<ActivityDTO> activities = userService.getUserActivities(authentication.getName());
        return ResponseEntity.ok(activities);
    }
}

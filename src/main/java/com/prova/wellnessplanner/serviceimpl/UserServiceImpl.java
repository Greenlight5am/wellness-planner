package com.prova.wellnessplanner.serviceimpl;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.dto.UserDTO;
import com.prova.wellnessplanner.entity.Activity;
import com.prova.wellnessplanner.entity.User;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.exeption.UserNotFoundException;
import com.prova.wellnessplanner.mapper.ActivityMapper;
import com.prova.wellnessplanner.mapper.UserMapper;
import com.prova.wellnessplanner.repository.ActivityRepository;
import com.prova.wellnessplanner.repository.UserRepository;
import com.prova.wellnessplanner.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementazione dell'interfaccia UserService.
 */
@Service
public class UserServiceImpl implements UserService {

    // Iniezione dei repository tramite campi privati final e costruttore
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public UserServiceImpl(UserRepository userRepository,
                           ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Converto il DTO in entità
        User user = UserMapper.toEntity(userDTO);

        // Salvo l'utente
        User savedUser = userRepository.save(user);

        // Converto l'entità salvata in DTO e la restituisco
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Integer id) throws UserNotFoundException {
        // Recupero l'utente per ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con id: " + id));

        // Converto l'entità in DTO e la restituisco
        return UserMapper.toDTO(user);
    }

    @Override
    public void subscribeToActivity(String email, Integer activityId) throws UserNotFoundException, ActivityNotFoundException {
        // Recupero l'utente utilizzando l'email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con email: " + email));

        // Recupero l'attività
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Attività non trovata con id: " + activityId));

        // Verifico se l'utente è già iscritto all'attività
        if (user.getActivities().contains(activity)) {
            throw new IllegalStateException("L'utente è già iscritto a questa attività.");
        }

        // Verifico se l'utente è già impegnato in quell'orario con un'altra attività
        boolean isOverlapping = user.getActivities().stream().anyMatch(existingActivity ->
                existingActivity.getDataOraInizio().isBefore(activity.getDataOraFine()) &&
                        existingActivity.getDataOraFine().isAfter(activity.getDataOraInizio())
        );

        if (isOverlapping) {
            throw new IllegalStateException("L'utente è già impegnato in un'altra attività durante questo orario.");
        }

        // Verifico disponibilità posti e data di scadenza
        if (activity.getPostiDisponibili() > activity.getPostiOccupati()
                && activity.getDataOraFine().isAfter(LocalDateTime.now())) {

            // Aggiungo l'attività all'utente e viceversa
            user.getActivities().add(activity);
            activity.getUsers().add(user);

            // Incremento i posti occupati
            activity.setPostiOccupati(activity.getPostiOccupati() + 1);

            // Salvo le modifiche
            userRepository.save(user);
            activityRepository.save(activity);
        } else {
            throw new IllegalStateException("Non è possibile iscriversi a questa attività.");
        }
    }

    @Override
    public void unsubscribeFromActivity(String email, Integer activityId) throws UserNotFoundException, ActivityNotFoundException {
        // Recupero l'utente e l'attività
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con email: " + email));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Attività non trovata con id: " + activityId));

        // Rimuovo l'attività dall'utente e viceversa
        if (user.getActivities().remove(activity)) {
            activity.getUsers().remove(user);

            // Decremento i posti occupati
            activity.setPostiOccupati(activity.getPostiOccupati() - 1);

            // Salvo le modifiche
            userRepository.save(user);
            activityRepository.save(activity);
        } else {
            throw new IllegalStateException("L'utente non è iscritto a questa attività.");
        }
    }

    @Override
    public List<ActivityDTO> getUserActivities(String email) throws UserNotFoundException {
        // Recupero l'utente
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con email: " + email));

        // Recupero le attività a cui l'utente è iscritto e le converto in DTO
        return user.getActivities().stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList());
    }
}

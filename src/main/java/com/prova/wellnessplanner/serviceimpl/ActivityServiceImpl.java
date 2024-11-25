package com.prova.wellnessplanner.serviceimpl;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.entity.Activity;
import com.prova.wellnessplanner.entity.Category;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.mapper.ActivityMapper;
import com.prova.wellnessplanner.repository.ActivityRepository;
import com.prova.wellnessplanner.repository.CategoryRepository;
import com.prova.wellnessplanner.service.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementazione dell'interfaccia ActivityService.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    // Iniezione dei repository tramite campi privati final e costruttore
    private final ActivityRepository activityRepository;
    private final CategoryRepository categoryRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository,
                               CategoryRepository categoryRepository) {
        this.activityRepository = activityRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        // Converto il DTO in entità
        Activity activity = ActivityMapper.toEntity(activityDTO);

        // Imposta postiOccupati a 0 se non specificato
        activity.setPostiOccupati(activityDTO.getPostiOccupati() != null ? activityDTO.getPostiOccupati() : 0);

        // Verifica che postiOccupati non superi postiDisponibili
        if (activity.getPostiOccupati() > activityDTO.getPostiDisponibili()) {
            throw new IllegalArgumentException("Posti occupati non possono superare i posti disponibili.");
        }

        // Gestisco le categorie
        if (activityDTO.getCategorie() != null) {
            Set<Category> categories = activityDTO.getCategorie().stream()
                    .map(nomeCategoria -> categoryRepository.findByNome(nomeCategoria)
                            .orElseGet(() -> {
                                // Se la categoria non esiste, la creo
                                Category newCategory = new Category();
                                newCategory.setNome(nomeCategoria);
                                return categoryRepository.save(newCategory);
                            }))
                    .collect(Collectors.toSet());
            activity.setCategories(categories);
        }

        // Salvo l'attività
        Activity savedActivity = activityRepository.save(activity);

        // Converto l'entità salvata in DTO e la restituisco
        return ActivityMapper.toDTO(savedActivity);
    }

    @Override
    public ActivityDTO updateActivity(Integer id, ActivityDTO activityDTO) throws ActivityNotFoundException {
        // Recupero l'attività esistente
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Attività non trovata con id: " + id));

        // Aggiorno i campi necessari
        existingActivity.setNome(activityDTO.getNome());
        existingActivity.setLuogo(activityDTO.getLuogo());
        existingActivity.setDataOraInizio(activityDTO.getDataOraInizio());
        existingActivity.setDataOraFine(activityDTO.getDataOraFine());
        existingActivity.setPostiDisponibili(activityDTO.getPostiDisponibili());

        // Gestisco le categorie
        if (activityDTO.getCategorie() != null) {
            Set<Category> categories = activityDTO.getCategorie().stream()
                    .map(nomeCategoria -> categoryRepository.findByNome(nomeCategoria)
                            .orElseGet(() -> {
                                Category newCategory = new Category();
                                newCategory.setNome(nomeCategoria);
                                return categoryRepository.save(newCategory);
                            }))
                    .collect(Collectors.toSet());
            existingActivity.setCategories(categories);
        }

        // Salvo l'attività aggiornata
        Activity updatedActivity = activityRepository.save(existingActivity);

        // Converto l'entità aggiornata in DTO e la restituisco
        return ActivityMapper.toDTO(updatedActivity);
    }

    @Override
    public void deleteActivity(Integer id) throws ActivityNotFoundException {
        // Verifico se l'attività esiste
        if (!activityRepository.existsById(id)) {
            throw new ActivityNotFoundException("Attività non trovata con id: " + id);
        }
        // Elimino l'attività
        activityRepository.deleteById(id);
    }

    @Override
    public ActivityDTO getActivityById(Integer id) throws ActivityNotFoundException {
        // Recupero l'attività per ID
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Attività non trovata con id: " + id));

        // Converto l'entità in DTO e la restituisco
        return ActivityMapper.toDTO(activity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        // Recupero tutte le attività e le converto in DTO
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityDTO> searchActivities(String nome, LocalDate giorno, Boolean disponibilita) {
        // Se 'giorno' è null, impostalo al giorno corrente o al giorno successivo
        if (giorno == null) {
            giorno = LocalDate.now(); // Imposta al giorno corrente se non è specificato
        }
        LocalDateTime startOfDay = giorno.atStartOfDay();
        LocalDateTime endOfDay = giorno.atTime(23, 59, 59);


        List<Activity> activities = activityRepository.findByFilters(
                nome != null ? nome.toLowerCase() : null,
                startOfDay,
                endOfDay,
                disponibilita,
                LocalDateTime.now()
        );

        // Converte le attività trovate in DTO e le restituisce
        return activities.stream()
                .map(ActivityMapper::toDTO)
                .collect(Collectors.toList());
    }

}

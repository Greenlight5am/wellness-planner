package com.prova.wellnessplanner.mapper;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.entity.Activity;
import com.prova.wellnessplanner.entity.Media;
import com.prova.wellnessplanner.entity.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper per convertire tra Activity e ActivityDTO.
 */
@Component
public class ActivityMapper {

    public static ActivityDTO toDTO(Activity activity) {
        ActivityDTO dto = new ActivityDTO();
        dto.setId(activity.getId());
        dto.setNome(activity.getNome());
        dto.setLuogo(activity.getLuogo());
        dto.setDataOraInizio(activity.getDataOraInizio());
        dto.setDataOraFine(activity.getDataOraFine());
        dto.setPostiDisponibili(activity.getPostiDisponibili());
        dto.setPostiOccupati(activity.getPostiOccupati());

        // Conversione delle categorie
        if (activity.getCategories() != null) {
            dto.setCategorie(activity.getCategories().stream()
                    .map(Category::getNome)
                    .collect(Collectors.toSet()));
        }

        // Imposto l'URL del media e l'ID del media se presente
        Media media = activity.getMedia();
        if (media != null) {
            dto.setMediaUrl("/api/media/" + media.getId());
            dto.setMediaId(media.getId()); // Aggiunto per impostare mediaId
        }

        return dto;
    }

    public static Activity toEntity(ActivityDTO dto) {
        Activity activity = new Activity();
        activity.setId(dto.getId());
        activity.setNome(dto.getNome());
        activity.setLuogo(dto.getLuogo());
        activity.setDataOraInizio(dto.getDataOraInizio());
        activity.setDataOraFine(dto.getDataOraFine());
        activity.setPostiDisponibili(dto.getPostiDisponibili());
        activity.setPostiOccupati(dto.getPostiOccupati());

        return activity;
    }
}


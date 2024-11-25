package com.prova.wellnessplanner.mapper;

import com.prova.wellnessplanner.dto.MediaDTO;
import com.prova.wellnessplanner.entity.Media;
import org.springframework.stereotype.Component;

/**
 * Mapper per convertire tra Media e MediaDTO.
 */
@Component
public class MediaMapper {

    public static MediaDTO toDTO(Media media) {
        MediaDTO dto = new MediaDTO();
        dto.setId(media.getId());
        dto.setFileName(media.getFileName());
        dto.setFileType(media.getFileType());

        // Imposto l'URL per accedere al media
        dto.setUrl("/api/media/" + media.getId());

        return dto;
    }

    public static Media toEntity(MediaDTO dto) {
        Media media = new Media();
        media.setId(dto.getId());
        media.setFileName(dto.getFileName());
        media.setFileType(dto.getFileType());

        return media;
    }
}

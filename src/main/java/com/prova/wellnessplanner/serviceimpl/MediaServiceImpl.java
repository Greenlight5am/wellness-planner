package com.prova.wellnessplanner.serviceimpl;

import com.prova.wellnessplanner.dto.ActivityDTO;
import com.prova.wellnessplanner.entity.Media;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import com.prova.wellnessplanner.mapper.ActivityMapper;
import com.prova.wellnessplanner.repository.ActivityRepository;
import com.prova.wellnessplanner.repository.MediaRepository;
import com.prova.wellnessplanner.service.ActivityService;
import com.prova.wellnessplanner.service.MediaService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    private final ActivityRepository activityRepository;

    public MediaServiceImpl(MediaRepository mediaRepository, ActivityRepository activityRepository) {
        this.mediaRepository = mediaRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Media uploadMediaForActivity(Integer activityId, MultipartFile file, ActivityService activityService) throws IOException, ActivityNotFoundException {
        // Carica il media
        Media media = new Media();
        media.setFileName(file.getOriginalFilename());
        media.setFileType(file.getContentType());
        media.setData(file.getBytes());
        Media savedMedia = mediaRepository.save(media);

        // Aggiorna l'attività associata con il nuovo media
        ActivityDTO activityDTO = ActivityMapper.toDTO(activityRepository.findById(activityId).orElseThrow());
        activityDTO.setMediaId(savedMedia.getId());
        activityService.updateActivity(activityId, activityDTO);

        return savedMedia;
    }

    @Override
    public Media getMediaById(Integer id) {

        //Recupero un media in base all'ID
        return mediaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Media non trovato con id: " + id));
    }
}

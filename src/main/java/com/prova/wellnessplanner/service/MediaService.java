package com.prova.wellnessplanner.service;

import com.prova.wellnessplanner.entity.Media;
import com.prova.wellnessplanner.exeption.ActivityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {

     Media uploadMediaForActivity(Integer activityId, MultipartFile file, ActivityService activityService) throws IOException, ActivityNotFoundException;

    Media getMediaById(Integer id);
}

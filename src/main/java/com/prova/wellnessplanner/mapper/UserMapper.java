package com.prova.wellnessplanner.mapper;

import com.prova.wellnessplanner.dto.UserDTO;
import com.prova.wellnessplanner.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper per convertire tra User e UserDTO.
 */
@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setCognome(user.getCognome());
        dto.setEmail(user.getEmail());

        // Conversione delle attività
        if (user.getActivities() != null) {
            dto.setActivityIds(user.getActivities().stream()
                    .map(activity -> activity.getId())
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setCognome(dto.getCognome());
        user.setEmail(dto.getEmail());

        return user;
    }
}

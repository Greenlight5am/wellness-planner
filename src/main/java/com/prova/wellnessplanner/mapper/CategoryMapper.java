package com.prova.wellnessplanner.mapper;

import com.prova.wellnessplanner.dto.CategoryDTO;
import com.prova.wellnessplanner.entity.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper per convertire tra Category e CategoryDTO.
 */
@Component
public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setNome(category.getNome());

        // Conversione delle attività associate
        if (category.getActivities() != null) {
            dto.setActivityIds(category.getActivities().stream()
                    .map(activity -> activity.getId())
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setNome(dto.getNome());

        return category;
    }
}

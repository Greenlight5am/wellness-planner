package com.prova.wellnessplanner.repository;

import com.prova.wellnessplanner.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByNome(String nome);
}

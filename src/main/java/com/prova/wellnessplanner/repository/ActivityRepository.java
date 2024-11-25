package com.prova.wellnessplanner.repository;

import com.prova.wellnessplanner.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByNomeContainingIgnoreCase(String nome);

    List<Activity> findByDataOraInizioBetween(LocalDateTime start, LocalDateTime end);

    List<Activity> findByPostiDisponibiliGreaterThanAndDataOraFineAfter(int postiOccupati, LocalDateTime now);

    List<Activity> findByNomeContainingIgnoreCaseAndDataOraInizioBetween(String nome, LocalDateTime start, LocalDateTime end);

    @Query(value = """
    SELECT a FROM Activity a WHERE 
    (:nome IS NULL OR LOWER(a.nome) LIKE CONCAT('%', LOWER(:nome), '%')) AND
    a.dataOraInizio >= COALESCE(:startOfDay, a.dataOraInizio) AND
    a.dataOraFine <= COALESCE(:endOfDay, a.dataOraFine) AND
    (
        :disponibilita IS NULL OR 
        (:disponibilita = TRUE AND a.postiDisponibili > a.postiOccupati AND a.dataOraFine > :now) OR
        (:disponibilita = FALSE AND (a.postiDisponibili <= a.postiOccupati OR a.dataOraFine <= :now))
    )
    """)
    List<Activity> findByFilters(
            @Param("nome") String nome,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay,
            @Param("disponibilita") Boolean disponibilita,
            @Param("now") LocalDateTime now
    );

}

package com.prova.wellnessplanner.dto;

import java.util.Set;

/**
 * DTO per trasferire i dati della Categoria.
 */
public class CategoryDTO {

    private Integer id;
    private String nome;
    private Set<Integer> activityIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Integer> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(Set<Integer> activityIds) {
        this.activityIds = activityIds;
    }
}

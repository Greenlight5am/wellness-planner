package com.prova.wellnessplanner.dto;

import java.util.Set;

/**
 * DTO per trasferire i dati dell'Utente.
 */
public class UserDTO {

    private Integer id;
    private String nome;
    private String cognome;
    private String email;
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Integer> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(Set<Integer> activityIds) {
        this.activityIds = activityIds;
    }
}

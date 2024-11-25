package com.prova.wellnessplanner.dto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO per trasferire i dati dell'Attività tra client e server.
 */
public class ActivityDTO {

    private Integer id;
    private String nome;
    private String luogo;
    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private Integer postiDisponibili;
    private Integer postiOccupati;
    private Set<String> categorie;
    private String mediaUrl;
    private Integer mediaId;

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

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public LocalDateTime getDataOraInizio() {
        return dataOraInizio;
    }

    public void setDataOraInizio(LocalDateTime dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    public LocalDateTime getDataOraFine() {
        return dataOraFine;
    }

    public void setDataOraFine(LocalDateTime dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    public Integer getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(Integer postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public Integer getPostiOccupati() {
        return postiOccupati;
    }

    public void setPostiOccupati(Integer postiOccupati) {
        this.postiOccupati = postiOccupati;
    }

    public Set<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(Set<String> categorie) {
        this.categorie = categorie;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    // Getter e Setter per mediaId

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
}



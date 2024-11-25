package com.prova.wellnessplanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.FetchType;


import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_activity")
public class Activity {

    private Integer id;
    private String nome;
    private String luogo;
    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private Integer postiDisponibili;
    private Integer postiOccupati;
    private Set<User> users;
    private Set<Category> categories;
    private Media media;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nome", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "luogo", nullable = false)
    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    @Column(name = "data_ora_inizio", nullable = false)
    public LocalDateTime getDataOraInizio() {
        return dataOraInizio;
    }

    public void setDataOraInizio(LocalDateTime dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    @Column(name = "data_ora_fine", nullable = false)
    public LocalDateTime getDataOraFine() {
        return dataOraFine;
    }

    public void setDataOraFine(LocalDateTime dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    @Column(name = "posti_disponibili", nullable = false)
    public Integer getPostiDisponibili() {
        return postiDisponibili;
    }

    public void setPostiDisponibili(Integer postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    @Column(name = "posti_occupati", nullable = false)
    public Integer getPostiOccupati() {
        return postiOccupati;
    }

    public void setPostiOccupati(Integer postiOccupati) {
        this.postiOccupati = postiOccupati;
    }

    @ManyToMany(mappedBy = "activities", fetch = FetchType.LAZY)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "activity_category",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @OneToOne
    @JoinColumn(name = "media_id")
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id != null && id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
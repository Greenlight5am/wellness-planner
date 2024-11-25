package com.prova.wellnessplanner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
@Table(name = "t_category")
public class Category {

    private Integer id;
    private String nome;
    private Set<Activity> activities;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Column(name = "nome", nullable = false, unique = true)
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @ManyToMany(mappedBy = "categories")
    public Set<Activity> getActivities() { return activities; }

    public void setActivities(Set<Activity> activities) { this.activities = activities; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}

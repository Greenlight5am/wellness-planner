package com.prova.wellnessplanner.entity;

import com.prova.wellnessplanner.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User implements UserDetails {

    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Set<Activity> activities;
    private Role role;

    public User(String nome, String cognome, String email, String password ,Role role) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Column(name = "nome", nullable = false)
    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Column(name = "cognome", nullable = false)
    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @Enumerated(EnumType.STRING)
    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    @Override
    @Column(name = "password", nullable = false)
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_activity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @Transient
    public String getUsername() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}


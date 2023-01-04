package com.example.demouser.entity;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private Instant modifiedAt;

    @Column(name = "last_login")
    @UpdateTimestamp
    private Instant lastLogin;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "token")
    private String token;

    @Column(name = "token_expiration")
    private Instant tokenExpiration;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Phones> phones = new java.util.ArrayList<>();
}










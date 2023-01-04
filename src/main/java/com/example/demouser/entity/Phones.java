package com.example.demouser.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Phones {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(name = "number")
    private String number;

    @Column(name = "city:code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;
}
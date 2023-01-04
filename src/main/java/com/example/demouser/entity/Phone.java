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
public class Phone {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false, referencedColumnName = "id")
    private User user;

    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;

}
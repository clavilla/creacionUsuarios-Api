package com.clavilla.userapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="city_code")
    private String citycode;

    @Column(name="country_code")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}

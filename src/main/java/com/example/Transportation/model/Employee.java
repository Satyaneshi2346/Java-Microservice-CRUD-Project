package com.example.Transportation.model;

import lombok.*;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String FirstNameLastName;

    @Column
    private String TransportationNeeded;

    @Column
    private String TravelDistance;

}
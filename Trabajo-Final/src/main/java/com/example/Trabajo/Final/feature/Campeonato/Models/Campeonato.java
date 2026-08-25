package com.example.Trabajo.Final.feature.Campeonato.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "campeonatos")
@AllArgsConstructor
@NoArgsConstructor
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String año;

    @OneToMany(mappedBy = "campeonato")
    @JsonBackReference
    private List<Partido> partidos =  new ArrayList<>();

}

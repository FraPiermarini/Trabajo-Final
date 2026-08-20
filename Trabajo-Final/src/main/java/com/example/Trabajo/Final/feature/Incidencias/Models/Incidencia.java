package com.example.Trabajo.Final.feature.Incidencias.Models;


import java.time.LocalDate;

import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugadorId", nullable = false)
    @JsonManagedReference
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "partidoId", nullable = true)
    @JsonManagedReference
    private Partido partido;

    @Column
    private LocalDate fecha;

    @Column
    private String motivo;

    @Column
    private Integer cantidadDias;
}

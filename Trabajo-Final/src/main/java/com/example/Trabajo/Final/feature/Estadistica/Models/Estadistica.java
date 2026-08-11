package com.example.Trabajo.Final.feature.Estadistica.Models;

import org.hibernate.annotations.Audited.Table;

import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "estadisticas")
@AllArgsConstructor
@NoArgsConstructor
public class Estadistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jugadorId")
    @JsonManagedReference
    private Jugador jugador;

    /*@ManyToOne
    @JoinColumn(name = "partidoId")
    @JsonManagedReference
    private Partido partido;*/

    private Integer minutos = 0;

    private Integer goles = 0;

    private Integer asistencias = 0;

    private Integer rojas = 0;

    private Integer amarillas = 0;

    private Boolean titular = false;




}

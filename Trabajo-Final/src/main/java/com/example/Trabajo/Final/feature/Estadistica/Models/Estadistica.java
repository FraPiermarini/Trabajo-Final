package com.example.Trabajo.Final.feature.Estadistica.Models;


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
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "partidoId")
    @JsonManagedReference
    private Partido partido;

    @Column
    private Integer minutos = 0;

    @Column
    private Integer goles = 0;
    
    @Column
    private Integer asistencias = 0;

    @Column
    private Integer rojas = 0;
 
    @Column
    private Integer amarillas = 0;
 
    @Column
    private Boolean titular = false;




}

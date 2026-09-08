package com.example.Trabajo.Final.feature.Partido.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Incidencias.Models.Incidencia;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name =  "partidos")
@AllArgsConstructor
@NoArgsConstructor
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String jornada;
    
    @Column
    private LocalDate fecha;
 
    @Column
    private String rival;

    @ManyToOne
    @JoinColumn(name = "campeonatoId")
    @JsonManagedReference
    private Campeonato campeonato; 

    @Column    
    private String resultado;

    @Column
    private Boolean local = true;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    @JsonManagedReference
    private Categoria categoria;

    @OneToMany(mappedBy = "partido")
    private List<Incidencia> incidencias = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "entrenadorId")
    private Entrenador entrenador;

}

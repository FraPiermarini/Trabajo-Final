package com.example.Trabajo.Final.feature.Jugador.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "jugadores")
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El dni es obligatorio")
    private String dni;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "La posicion es obligatoria")
    private String posicion;

    @NotNull(message = "El numero de camiseta es obligatorio")
    private Integer numeroCamiseta;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    @JsonManagedReference
    private Categoria categoria;  

    @OneToMany(mappedBy = "jugador")
    private List<Incidencia> incidencias = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String imagenUrl;


}

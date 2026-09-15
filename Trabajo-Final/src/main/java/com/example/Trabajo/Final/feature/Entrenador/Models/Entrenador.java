package com.example.Trabajo.Final.feature.Entrenador.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "entrenadores")
@AllArgsConstructor
@NoArgsConstructor
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column 
    private String nombre;

    @Column 
    private String apellido;

    @Column 
    private Integer edad;

    @OneToMany(mappedBy = "entrenador")
    @JsonBackReference
    private List<Categoria> categorias = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String imagenEntrenador;
}

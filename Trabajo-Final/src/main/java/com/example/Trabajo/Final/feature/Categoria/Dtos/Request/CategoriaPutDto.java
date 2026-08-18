package com.example.Trabajo.Final.feature.Categoria.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPutDto {
    private String nombre;
    private String año;
    private String descripcion;
    private Long entrenadorId;
}

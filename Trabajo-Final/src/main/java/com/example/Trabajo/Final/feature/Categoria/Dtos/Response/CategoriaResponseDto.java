package com.example.Trabajo.Final.feature.Categoria.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDto {
    private Long id;
    private String nombre;
    private String Año;
    private String descripcion;
    private Long entrenadorId;
    private String nombreEntrenador;

}

package com.example.Trabajo.Final.feature.Entrenador.Dtos.Response;

import java.util.List;

import com.example.Trabajo.Final.feature.Categoria.Dtos.CategoriaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private List<CategoriaDto> categorias;
}

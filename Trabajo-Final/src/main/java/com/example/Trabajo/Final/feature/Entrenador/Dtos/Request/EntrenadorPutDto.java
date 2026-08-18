package com.example.Trabajo.Final.feature.Entrenador.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorPutDto {
    private String nombre;
    private String apellido;
    private Integer edad;
}

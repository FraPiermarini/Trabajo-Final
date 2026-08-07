package com.example.Trabajo.Final.feature.Jugador.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorDto {
    private Long Id;
    private String nombre;
    private String apellido;
    private String posicion;
    private Integer numeroCamiseta;
    
}

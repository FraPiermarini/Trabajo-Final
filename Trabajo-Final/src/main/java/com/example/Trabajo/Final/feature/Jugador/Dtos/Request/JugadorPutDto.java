package com.example.Trabajo.Final.feature.Jugador.Dtos.Request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorPutDto {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String posicion;
    private Integer numeroCamiseta;
    private Long categoriaId;
}

package com.example.Trabajo.Final.feature.Jugador.Dtos.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNaciemiento;
    private String posicion;
    private Integer numeroCamiseta;
    private Long categoriaId;
    private String nombreCategoria;

}

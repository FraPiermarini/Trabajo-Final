package com.example.Trabajo.Final.feature.Estadistica.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEntrenadorResponseDto {
    private Long entrenadorId;
    private String entrenadorNombre;
    private String entrenadorApellido;
    private Integer partidos;
    private Integer victorias;
    private Integer derrotas;
    private Integer empates;
    private Double porcentajeVictorias;
    private Double porcentajeEmpates;
    private Double porcentajeDerrotas;
    private Integer goles;
    private Integer golesEnContra;
    private Integer diferenciaGol;
    private Integer puntos;
    private Double efectividad;
    private Double promedioGoles;
    private Double promedioGolesContra;
}

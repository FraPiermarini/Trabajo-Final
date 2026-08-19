package com.example.Trabajo.Final.feature.Estadistica.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadisticaResponseDto {
    private Long jugadorId;
    private Integer partidos;
    private Integer titulares;
    private Integer minutos;
    private Integer goles;
    private Integer asistencias;
    private Integer rojas;
    private Integer amarillas;
}

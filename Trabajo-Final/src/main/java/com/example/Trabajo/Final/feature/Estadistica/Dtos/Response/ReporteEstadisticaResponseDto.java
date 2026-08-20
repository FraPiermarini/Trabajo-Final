package com.example.Trabajo.Final.feature.Estadistica.Dtos.Response;

import java.util.List;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;

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
    private List<IncidenciaResponseDto> incidencias;
    private Integer diasIncidencias;
}

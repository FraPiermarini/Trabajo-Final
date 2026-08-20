package com.example.Trabajo.Final.feature.Incidencias.Dtos.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciaResponseDto {
    private Long id;
    private Long jugadorId;
    private Long partidoId;
    private LocalDate fecha;
    private String motivo;
    private Integer cantidadDias;
}

package com.example.Trabajo.Final.feature.Incidencias.Dtos.Request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciaPutDto {
    private Long jugadorId;
    private Long partidoId;
    private LocalDate fecha;
    private String motivo;
    private Integer cantidadDias;
}

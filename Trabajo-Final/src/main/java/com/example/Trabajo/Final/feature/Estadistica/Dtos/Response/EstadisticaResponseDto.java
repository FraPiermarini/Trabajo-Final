package com.example.Trabajo.Final.feature.Estadistica.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaResponseDto {
    private Long jugadorId;

    //private Long partidoId;

    private Integer minutos;

    private Integer goles;

    private Integer asistencias;

    private Integer rojas;

    private Integer amarillas;

    private Boolean titular;
}

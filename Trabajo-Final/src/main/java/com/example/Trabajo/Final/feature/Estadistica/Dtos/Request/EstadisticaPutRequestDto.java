package com.example.Trabajo.Final.feature.Estadistica.Dtos.Request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaPutRequestDto {
    @Min(0)
    private Integer minutos;

    @Min(0)
    private Integer goles;

    @Min(0)
    private Integer asistencias;

    @Min(0)
    private Integer rojas;

    @Min(0)
    private Integer amarillas;

    private Boolean titular;
}

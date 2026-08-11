package com.example.Trabajo.Final.feature.Estadistica.Dtos.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticaRequestDto {
    
    @NotNull(message = "El id del jugador es requerido")
    private Long jugadorId;

    @Min(0)
    private Integer minutos = 0;

    @Min(0)
    private Integer goles = 0;

    @Min(0)
    private Integer asistencias = 0;

    @Min(0)
    private Integer rojas = 0;

    @Min(0)
    private Integer amarillas = 0;

    @NotNull
    private Boolean titular;




}

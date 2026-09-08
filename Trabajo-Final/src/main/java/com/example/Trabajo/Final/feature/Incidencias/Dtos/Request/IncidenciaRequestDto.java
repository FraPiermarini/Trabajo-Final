package com.example.Trabajo.Final.feature.Incidencias.Dtos.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenciaRequestDto {
    @NotNull(message =  "El id del jugador es obligatorio")
    private Long jugadorId;

    private Long partidoId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    @NotNull(message = "La cantidad de dias es obligatorio")
    private Integer cantidadDias;
}

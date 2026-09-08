package com.example.Trabajo.Final.feature.Partido.Dtos.Request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoPutDto {
    private LocalDate fecha;
    private String jornada;
    private String rival;
    private Long campeonatoId;
    private String resultado;
    private Boolean local;
    private Long categoriaId;
    private Long entrenadorId;
}

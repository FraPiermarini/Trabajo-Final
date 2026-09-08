package com.example.Trabajo.Final.feature.Partido.Dtos.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoResponseDto {
    private Long id;

    private String jornada;
    
    private LocalDate fecha;

    private String rival;

    private Long campeonatoId;

    private String resultado;

    private Boolean local;

    private Long categoriaId;

    private Long entrenadorId;

}

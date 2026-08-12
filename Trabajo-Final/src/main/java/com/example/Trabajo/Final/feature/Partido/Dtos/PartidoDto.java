package com.example.Trabajo.Final.feature.Partido.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDto {
    private Long id;
    private String jornada;
    private String rival;
    private String resultado;
    private Boolean local;
}

package com.example.Trabajo.Final.feature.Campeonato.Dtos.Response;

import java.util.List;

import com.example.Trabajo.Final.feature.Partido.Dtos.PartidoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampeonatoResponseDto {
    
    private Long id;

    private String nombre;

    private String año;

    private List<PartidoDto> partidos;
}

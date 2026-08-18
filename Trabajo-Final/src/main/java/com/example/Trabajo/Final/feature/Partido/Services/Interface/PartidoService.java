package com.example.Trabajo.Final.feature.Partido.Services.Interface;

import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPatchDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPutDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;

public interface PartidoService {
    PartidoResponseDto actualizarPartido(Long id, PartidoPutDto dto);
    PartidoResponseDto actualizarResultadoPartido(Long id, PartidoPatchDto dto);
}

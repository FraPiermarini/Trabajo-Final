package com.example.Trabajo.Final.feature.Jugador.Services.Interface;

import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPatchDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPutDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;

public interface JugadorService {
    JugadorResponseDto actualizarJugador(Long id, JugadorPutDto dto);
    JugadorResponseDto actualizarNumeroJugador(Long id, JugadorPatchDto dto);

}

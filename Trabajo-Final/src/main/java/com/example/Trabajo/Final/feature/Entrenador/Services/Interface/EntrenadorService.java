package com.example.Trabajo.Final.feature.Entrenador.Services.Interface;

import com.example.Trabajo.Final.feature.Entrenador.Dtos.Request.EntrenadorPutDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Response.EntrenadorResponseDto;

public interface EntrenadorService {
    EntrenadorResponseDto actualizarEntrenador(Long id, EntrenadorPutDto dto);
}

package com.example.Trabajo.Final.feature.Campeonato.Services.Interface;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoPutDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;

public interface CampeonatoService {
    CampeonatoResponseDto actualizarCampeonato(Long id, CampeonatoPutDto dto);  
} 

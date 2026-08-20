package com.example.Trabajo.Final.feature.Incidencias.Services.Interface;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPatchDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPutDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;

public interface IncidenciaService {
    IncidenciaResponseDto actualizarIncidencia(Long id, IncidenciaPutDto dto);
    IncidenciaResponseDto actualizarDiasIncidencia(Long id, IncidenciaPatchDto dto);
}

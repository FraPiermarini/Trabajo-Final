package com.example.Trabajo.Final.feature.Incidencias.Controllers.patch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPatchDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Services.Interface.IncidenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins =  "*")
@RequiredArgsConstructor
public class IncidenciaPatchController {
    private final IncidenciaService incidenciaService;

    @PatchMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDto> actualizarDiasIncidencia(@PathVariable Long id, @RequestBody IncidenciaPatchDto dto){
        IncidenciaResponseDto respuesta = incidenciaService.actualizarDiasIncidencia(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

package com.example.Trabajo.Final.feature.Incidencias.Controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPutDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Services.Interface.IncidenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins =  "*")
@RequiredArgsConstructor
public class IncidenciaPutController {
    private final IncidenciaService incidenciaService;

    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDto> actualizarIncidencia(@PathVariable Long id, @RequestBody  IncidenciaPutDto dto){
        IncidenciaResponseDto respuesta = incidenciaService.actualizarIncidencia(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

package com.example.Trabajo.Final.feature.Incidencias.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Services.IncidenciaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IncidenciaGetController {
    private final IncidenciaServiceImpl incidenciaServiceImpl;

    @GetMapping
    public ResponseEntity<List<IncidenciaResponseDto>> obtenerIncidencias(){
        return ResponseEntity.ok(incidenciaServiceImpl.obtenerIncidencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDto> obtenerIncidencia(@PathVariable Long id){
        return ResponseEntity.ok(incidenciaServiceImpl.obtenerIncidencia(id));
    }
}

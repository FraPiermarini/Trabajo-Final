package com.example.Trabajo.Final.feature.Incidencias.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaRequestDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Services.IncidenciaServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Incidencias", description = "Operaciones relacionadas con incidencias")
@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaPostController {
    @Autowired
    private IncidenciaServiceImpl incidenciaServiceImpl;

    @PostMapping
    public IncidenciaResponseDto crearIncidencia(@RequestBody IncidenciaRequestDto dto){
        return incidenciaServiceImpl.crearIncidencia(dto);
    }
}

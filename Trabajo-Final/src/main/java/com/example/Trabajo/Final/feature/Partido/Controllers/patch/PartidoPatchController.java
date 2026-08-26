package com.example.Trabajo.Final.feature.Partido.Controllers.patch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPatchDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Services.Interface.PartidoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "Partido", description = "Operaciones relacionadas con partidos")
@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartidoPatchController {
    private final PartidoService partidoService;

    @PatchMapping("/{id}")
    public ResponseEntity<PartidoResponseDto> actualizarResultadoPartido(@PathVariable Long id, @RequestBody PartidoPatchDto dto){
        PartidoResponseDto respuesta = partidoService.actualizarResultadoPartido(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

package com.example.Trabajo.Final.feature.Partido.Controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPutDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Services.Interface.PartidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartidoPutController {
    private final PartidoService partidoService;

     @PutMapping("/{id}")
    public ResponseEntity<PartidoResponseDto> actualizarPartido(@PathVariable Long id, @RequestBody PartidoPutDto dto){
        PartidoResponseDto respuesta = partidoService.actualizarPartido(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

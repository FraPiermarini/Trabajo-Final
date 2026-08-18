package com.example.Trabajo.Final.feature.Campeonato.Controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoPutDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;
import com.example.Trabajo.Final.feature.Campeonato.Services.Interface.CampeonatoService;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPutDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/campeonatos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CampeonatoPutController {
    private final CampeonatoService campeonatoService;

     @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDto> actualizarCampeonato(@PathVariable Long id, @RequestBody CampeonatoPutDto dto){
        CampeonatoResponseDto respuesta = campeonatoService.actualizarCampeonato(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

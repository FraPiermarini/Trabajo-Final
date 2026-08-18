package com.example.Trabajo.Final.feature.Jugador.Controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPutDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Services.Interface.JugadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JugadorPutController {
    private final JugadorService jugadorService;

    @PutMapping("/{id}")
    public ResponseEntity<JugadorResponseDto> actualizarJugador(@PathVariable Long id, @RequestBody JugadorPutDto dto){
        JugadorResponseDto respuesta = jugadorService.actualizarJugador(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

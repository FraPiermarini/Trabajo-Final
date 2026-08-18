package com.example.Trabajo.Final.feature.Jugador.Controllers.patch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPatchDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Services.Interface.JugadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins =  "*")
@RequiredArgsConstructor
public class JugadorPatchController {
    private final JugadorService jugadorService;

    @PatchMapping("/{id}")
    public ResponseEntity<JugadorResponseDto> actualizarNumeroJugador(@PathVariable Long id, @RequestBody JugadorPatchDto dto){
        JugadorResponseDto respuesta = jugadorService.actualizarNumeroJugador(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

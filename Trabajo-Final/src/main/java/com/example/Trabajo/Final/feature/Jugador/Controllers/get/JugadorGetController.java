package com.example.Trabajo.Final.feature.Jugador.Controllers.get;

import com.example.Trabajo.Final.feature.Categoria.Services.CategoriaServiceImpl;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Jugador.Services.JugadorServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JugadorGetController {
    private final JugadorRepository jugadorRepository;
    private final JugadorServiceImpl jugadorServiceImpl;

    @GetMapping
    public ResponseEntity<List<Jugador>> obtenerJugadores(){
        return ResponseEntity.ok(jugadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorResponseDto> obtenerJugador(@PathVariable Long id){
        return ResponseEntity.ok(jugadorServiceImpl.obtenerJugador(id));
    }

}

package com.example.Trabajo.Final.feature.Jugador.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorRequestDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Services.JugadorServiceImpl;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorPostController {
    @Autowired
    private JugadorServiceImpl jugadorServiceImpl;

    @PostMapping
    public JugadorResponseDto crearJugador(@RequestBody JugadorRequestDto dto){
        return jugadorServiceImpl.crearJugador(dto);
    }
    
}

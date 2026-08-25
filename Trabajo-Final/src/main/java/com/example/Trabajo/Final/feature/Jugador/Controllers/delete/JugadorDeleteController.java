package com.example.Trabajo.Final.feature.Jugador.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Jugador.Services.JugadorServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Jugadores", description = "Operaciones relacionadas con los jugadores")
@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JugadorDeleteController {
    @Autowired
    private JugadorServiceImpl jugadorServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id){
        jugadorServiceImpl.eliminarJugador(id);
        return ResponseEntity.noContent().build();
    }
}

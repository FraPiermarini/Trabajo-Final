package com.example.Trabajo.Final.feature.Jugador.Controllers.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Trabajo.Final.feature.Jugador.Services.JugadorServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "Jugadores", description = "Operaciones relacionadas con los jugadores")
@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
public class JugadorPostImagenController {
    private final JugadorServiceImpl jugadorServiceImpl;

    @PostMapping("/{id}/imagen")
    public ResponseEntity<?> importarImagen(@PathVariable Long id, @RequestParam("imagen") MultipartFile imagen ){
        return ResponseEntity.ok(jugadorServiceImpl.importarImagen(id, imagen));
    }
}

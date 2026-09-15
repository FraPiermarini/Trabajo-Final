package com.example.Trabajo.Final.feature.Entrenador.Controladores.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Trabajo.Final.feature.Entrenador.Services.EntrenadorServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name =  "Entrenadores", description = "Operaciones relacionadas con entrenador")
@RestController 
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor 
public class EntrenadorImagenPostController {
    private final EntrenadorServiceImpl entrenadorServiceImpl;

    @PostMapping("/{id}/imagenEntrenador")
    public ResponseEntity<?> importarImagen(@PathVariable Long id, @RequestParam("imagen") MultipartFile imagen ){
        return ResponseEntity.ok(entrenadorServiceImpl.importarImagen(id, imagen));
    }
}

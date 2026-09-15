package com.example.Trabajo.Final.feature.Partido.Controllers.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Trabajo.Final.feature.Partido.Services.PartidoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Partidos", description = "Operaciones relacionadas con los partidos")
@RestController 
@RequestMapping ("/api/partidos")
@RequiredArgsConstructor 
public class ImagenRivalPostController {
    private  final PartidoServiceImpl partidoServiceImpl;

    @PostMapping("/{id}/imagenRival")
    public ResponseEntity<?> importarImagen(@PathVariable Long id, @RequestParam("imagen") MultipartFile imagen ){
        return ResponseEntity.ok(partidoServiceImpl.importarImagen(id, imagen));
    }
    
}

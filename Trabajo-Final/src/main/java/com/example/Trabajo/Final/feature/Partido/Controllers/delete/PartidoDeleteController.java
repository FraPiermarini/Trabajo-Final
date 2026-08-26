package com.example.Trabajo.Final.feature.Partido.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Partido.Services.PartidoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "Partido", description = "Operaciones relacionadas con partidos")
@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartidoDeleteController {
    @Autowired
    private PartidoServiceImpl partidoServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id){
        partidoServiceImpl.eliminarPartido(id);
        return ResponseEntity.noContent().build();
    }
}

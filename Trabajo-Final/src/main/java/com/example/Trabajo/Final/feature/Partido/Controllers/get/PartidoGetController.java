package com.example.Trabajo.Final.feature.Partido.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;
import com.example.Trabajo.Final.feature.Partido.Services.PartidoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name = "Partido", description = "Operaciones relacionadas con partidos")
@RestController
@RequestMapping("/api/partidos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PartidoGetController {
    private final PartidoServiceImpl partidoServiceImpl;

    @GetMapping
    public ResponseEntity<List<PartidoResponseDto>> obtenerPartidos(){
        return ResponseEntity.ok(partidoServiceImpl.obtenerPartidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoResponseDto> obtenerPartido(@PathVariable Long id){
        return ResponseEntity.ok(partidoServiceImpl.obtenerPartido(id));
    }
}

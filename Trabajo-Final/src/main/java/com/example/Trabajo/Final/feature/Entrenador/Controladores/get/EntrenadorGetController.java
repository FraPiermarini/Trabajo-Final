package com.example.Trabajo.Final.feature.Entrenador.Controladores.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Entrenador.Dtos.Response.EntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Entrenador.Repositories.EntrenadorRepository;
import com.example.Trabajo.Final.feature.Entrenador.Services.EntrenadorServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EntrenadorGetController {
    private final EntrenadorServiceImpl entrenadorServiceImpl;

    @GetMapping
    public ResponseEntity<List<EntrenadorResponseDto>> obtenerEntrenadores(){
        return ResponseEntity.ok(entrenadorServiceImpl.obtenerEntrenadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDto> obtenerEntrenador(@PathVariable Long id){
        return ResponseEntity.ok(entrenadorServiceImpl.obtenerEntrenador(id));
    }
    
}

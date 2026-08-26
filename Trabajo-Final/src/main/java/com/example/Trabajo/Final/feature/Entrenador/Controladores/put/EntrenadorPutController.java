package com.example.Trabajo.Final.feature.Entrenador.Controladores.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Entrenador.Dtos.Request.EntrenadorPutDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Response.EntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Entrenador.Services.Interface.EntrenadorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Entrenadores", description = "Operaciones relacionadas con entrenador")
@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EntrenadorPutController {
    private final EntrenadorService entrenadorService;

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponseDto> actualizarEntrenador(@PathVariable Long id, @RequestBody EntrenadorPutDto dto){
        EntrenadorResponseDto respuesta = entrenadorService.actualizarEntrenador(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}


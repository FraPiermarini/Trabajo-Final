package com.example.Trabajo.Final.feature.Campeonato.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;
import com.example.Trabajo.Final.feature.Campeonato.Services.CampeonatoServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/campeonatos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CampeonatoGetController {
    private final CampeonatoServiceImpl campeonatoServiceImpl;

    @GetMapping
    public ResponseEntity<List<CampeonatoResponseDto>> obtenerCampeonatos(){
        return ResponseEntity.ok(campeonatoServiceImpl.obtenerCampeonatos());    
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponseDto> obtenerCampeoanto(@PathVariable Long id) {
        return ResponseEntity.ok(campeonatoServiceImpl.obtenerCampeonato(id));
    }


}

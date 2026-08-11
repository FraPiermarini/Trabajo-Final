package com.example.Trabajo.Final.feature.Estadistica.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Estadistica.Services.EstadisticaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estadisticas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstadisticaGetController {
    private final EstadisticaServiceImpl estadisticaServiceImpl;
    private final EstadisticaRepository estadisticaRepository;

    @GetMapping()
    public ResponseEntity<List<Estadistica>> obtenerEstadisticas(){
        return ResponseEntity.ok(estadisticaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticaResponseDto> obtenerEstadistica(@PathVariable Long id){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadistica(id));
    }

}

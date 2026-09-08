package com.example.Trabajo.Final.feature.Estadistica.Controllers.put;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaPutRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Services.EstadisticaServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
@Tag(name =  "Estadisticas", description = "Operaciones relacionadas con estadisticas")
public class EstadisticaPutController {
    private final EstadisticaServiceImpl estadisticaServiceImpl;

    @PutMapping("/{id}")
    public ResponseEntity<EstadisticaResponseDto> actualizarEstadistica(@PathVariable Long id, @RequestBody  EstadisticaPutRequestDto dto){
        EstadisticaResponseDto respuesta = estadisticaServiceImpl.actualizarEstadistica(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

package com.example.Trabajo.Final.feature.Estadistica.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteEstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Estadistica.Services.EstadisticaServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Estadisticas", description = "Operaciones relacionadas con estadisticas")
@RestController
@RequestMapping("/api/estadisticas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstadisticaGetController {
    private final EstadisticaServiceImpl estadisticaServiceImpl;

    @GetMapping()
    public ResponseEntity<List<EstadisticaResponseDto>> obtenerEstadisticas(){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticaResponseDto> obtenerEstadistica(@PathVariable Long id){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadistica(id));
    }

    @GetMapping("/jugador/{jugadorId}/acumulado")
    public ResponseEntity<ReporteEstadisticaResponseDto> obtenerEstadisticasAcumulada(@PathVariable Long jugadorId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticaAcumulada(jugadorId));
    }

    @GetMapping("/mes/{año}/{mes}")
    public ResponseEntity<List<ReporteEstadisticaResponseDto>> obtenerEstadisticasPorMes(@PathVariable Integer año, @PathVariable Integer mes){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticasPorMes(año, mes));
    }

    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<ReporteEstadisticaResponseDto>> obtenerEstadisticasPorCampeonato(@PathVariable Long campeonatoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticasPorCampeonato(campeonatoId));
    }

    @GetMapping("/partido/{partidoId}")
    public ResponseEntity<List<ReporteEstadisticaResponseDto>> obtenerEstadisticasPorPartido(@PathVariable Long partidoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticasPorPartido(partidoId));
    }

    @GetMapping("/partido/{partidoId}/jugador/{jugadorId}")
    public ResponseEntity<ReporteEstadisticaResponseDto> obtenerEstadisticasPartidoJugador(@PathVariable Long partidoId, @PathVariable Long jugadorId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticaPartidoJugador(partidoId, jugadorId));
    }

    @GetMapping("/mes/{año}/{mes}/jugador/{jugadorId}")
    public ResponseEntity<ReporteEstadisticaResponseDto> obtenerEstadisticasMesJugador(@PathVariable Integer año, @PathVariable Integer mes, @PathVariable Long jugadorId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticasMesJugador(año, mes, jugadorId));
    }

    @GetMapping("/campeonato/{campeonatoId}/jugador/{jugadorId}")
    public ResponseEntity<ReporteEstadisticaResponseDto> obtenerEstadisticasCampeonatoJugador(@PathVariable Long campeonatoId, @PathVariable Long jugadorId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerEstadisticasCampeonatoJugador(campeonatoId, jugadorId));
    }




}

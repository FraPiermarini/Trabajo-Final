package com.example.Trabajo.Final.feature.Estadistica.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteCategoriaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteEntrenadorResponseDto;
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

    @GetMapping("/entrenador/acumulado")
    public ResponseEntity<List<ReporteEntrenadorResponseDto>> obtenerReporteEntrenadoresAcumulado(){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadoresAcumulado());
    } 

    @GetMapping("/entrenador/mes/{año}/{mes}")
    public ResponseEntity<List<ReporteEntrenadorResponseDto>> obtenerReporteEntrenadoresPorMes(@PathVariable Integer año, @PathVariable Integer mes){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadoresPorMes(año, mes));
    }

    @GetMapping("/entrenador/campeonato/{campeonatoId}")
    public ResponseEntity<List<ReporteEntrenadorResponseDto>> obtenerReporteEntrenadoresPorCampeonato(@PathVariable Long campeonatoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadoresPorCampeonato(campeonatoId));
    }

    @GetMapping("/entrenador/partido/{partidoId}")
    public ResponseEntity<List<ReporteEntrenadorResponseDto>> obtenerReporterEntrenadoresPorPartido(@PathVariable Long partidoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadoresPorPartido(partidoId));
    }

    @GetMapping("/entrenador/{entrenadorId}/acumulado/")
    public ResponseEntity<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresAcumulado(@PathVariable Long entrenadorId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadorAcumulado(entrenadorId));
    } 

    @GetMapping("/entrenador/{entrenadorId}/mes/{año}/{mes}")
    public ResponseEntity<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresPorMes(@PathVariable Long entrenadorId, @PathVariable Integer año, @PathVariable Integer mes){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadorPorMes(entrenadorId, año, mes));
    }

    @GetMapping("/entrenador/{entrenadorId}/campeonato/{campeonatoId}")
    public ResponseEntity<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresPorCampeonato(@PathVariable Long entrenadorId, @PathVariable Long campeonatoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadorPorCampeonato(entrenadorId, campeonatoId));
    }

    @GetMapping("/entrenador/{entrenadorId}/partido/{partidoId}")
    public ResponseEntity<ReporteEntrenadorResponseDto> obtenerReporterEntrenadoresPorPartido(@PathVariable Long entrenadorId, @PathVariable Long partidoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteEntrenadorPorPartido(entrenadorId, partidoId));
    }

    @GetMapping("/categoria/acumulado")
    public ResponseEntity<List<ReporteCategoriaResponseDto>> obtenerReporteCategoriasAcumulado(){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriasAcumulado());
    }

    @GetMapping("/categoria/mes/{año}/{mes}")
    public ResponseEntity<List<ReporteCategoriaResponseDto>> obtenerReporteCategoriasPorMes(@PathVariable Integer año, @PathVariable Integer mes){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriasPorMes(año, mes));
    }

    @GetMapping("/categoria/campeonato/{campeonatoId}")
    public ResponseEntity<List<ReporteCategoriaResponseDto>> obtenerReporteCategoriasPorCampeonato(@PathVariable Long campeonatoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriasPorCampeonato(campeonatoId));
    }

    @GetMapping("/categoria/partido/{partidoId}")
    public ResponseEntity<List<ReporteCategoriaResponseDto>> obtenerReporteCategoriaPorPartido(@PathVariable Long partidoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriasPorPartido(partidoId));
    }

    
    @GetMapping("/categoria/{categoriaId}/acumulado")
    public ResponseEntity<ReporteCategoriaResponseDto> obtenerReporteCategoriasAcumulado(@PathVariable Long categoriaId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriaAcumulado(categoriaId));
    }

    @GetMapping("/categoria/{categoriaId}/mes/{año}/{mes}")
    public ResponseEntity<ReporteCategoriaResponseDto> obtenerReporteCategoriasPorMes(@PathVariable Long categoriaId, @PathVariable Integer año, @PathVariable Integer mes){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriaPorMes(categoriaId, año, mes));
    }

    @GetMapping("/categoria/{categoriaId}/campeonato/{campeonatoId}")
    public ResponseEntity<ReporteCategoriaResponseDto> obtenerReporteCategoriasPorCampeonato(@PathVariable Long categoriaId, @PathVariable Long campeonatoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriaPorCampeonato(categoriaId, campeonatoId));
    }

    @GetMapping("/categoria/{categoriaId}/partido/{partidoId}")
    public ResponseEntity<ReporteCategoriaResponseDto> obtenerReporteCategoriaPorPartido(@PathVariable Long categoriaId, @PathVariable Long partidoId){
        return ResponseEntity.ok(estadisticaServiceImpl.obtenerReporteCategoriaPorPartido(categoriaId, partidoId));
    }

    




}

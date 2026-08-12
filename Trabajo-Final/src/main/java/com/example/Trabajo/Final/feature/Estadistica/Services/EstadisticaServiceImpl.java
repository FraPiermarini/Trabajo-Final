package com.example.Trabajo.Final.feature.Estadistica.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl {
    private final EstadisticaRepository estadisticaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    public EstadisticaResponseDto crearEstadistica(EstadisticaRequestDto dto){
        Estadistica nuevaEstadistica = new Estadistica();
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        nuevaEstadistica.setJugador(jugador);
        nuevaEstadistica.setMinutos(dto.getMinutos());
        nuevaEstadistica.setGoles(dto.getGoles());
        nuevaEstadistica.setAsistencias(dto.getAsistencias());
        nuevaEstadistica.setRojas(dto.getRojas());
        nuevaEstadistica.setAmarillas((dto.getAmarillas()));
        nuevaEstadistica.setTitular(dto.getTitular());
        Estadistica guardado = estadisticaRepository.save(nuevaEstadistica);

        
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
        if (guardado.getJugador() != null){
        respuesta.setJugadorId(guardado.getJugador().getId());   
        }
        respuesta.setMinutos(guardado.getMinutos());
        respuesta.setGoles(guardado.getGoles());
        respuesta.setAsistencias(guardado.getAsistencias());
        respuesta.setRojas(guardado.getRojas());
        respuesta.setAmarillas(guardado.getAmarillas());
        respuesta.setTitular(guardado.getTitular());
        return respuesta;
    }

    public List<EstadisticaResponseDto> obtenerEstadisticas(){
        List<Estadistica> estadisticas = estadisticaRepository.findAll();
        return estadisticas.stream().map(estadistica -> {
            EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
            if(estadistica.getJugador() != null){
                respuesta.setJugadorId(estadistica.getJugador().getId());
            }
            if(estadistica.getPartido() != null){
                respuesta.setPartidoId(estadistica.getPartido().getId());
            }
            respuesta.setMinutos(estadistica.getMinutos());
            respuesta.setGoles(estadistica.getGoles());
            respuesta.setAsistencias(estadistica.getAsistencias());
            respuesta.setRojas(estadistica.getRojas());
            respuesta.setAmarillas(estadistica.getAmarillas());
            respuesta.setTitular(estadistica.getTitular());
            return respuesta;
        }).toList();
    }

    public EstadisticaResponseDto obtenerEstadistica(Long id){
        Estadistica estadistica = estadisticaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estadistica no encontrada"));
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
        if (estadistica.getJugador() != null){
        respuesta.setJugadorId(estadistica.getJugador().getId());   
        }
        if (estadistica.getPartido() != null){
        respuesta.setPartidoId(estadistica.getPartido().getId());   
        }
        respuesta.setMinutos(estadistica.getMinutos());
        respuesta.setGoles(estadistica.getGoles());
        respuesta.setAsistencias(estadistica.getAsistencias());
        respuesta.setRojas(estadistica.getRojas());
        respuesta.setAmarillas(estadistica.getAmarillas());
        respuesta.setTitular(estadistica.getTitular());
        return respuesta;
    }

    public void eliminarEstadistica(Long id){
        estadisticaRepository.deleteById(id);
    }
}

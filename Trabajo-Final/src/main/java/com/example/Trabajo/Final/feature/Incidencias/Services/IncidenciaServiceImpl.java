package com.example.Trabajo.Final.feature.Incidencias.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPatchDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaPutDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Request.IncidenciaRequestDto;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Models.Incidencia;
import com.example.Trabajo.Final.feature.Incidencias.Repositories.IncidenciaRepository;
import com.example.Trabajo.Final.feature.Incidencias.Services.Interface.IncidenciaService;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidenciaServiceImpl implements IncidenciaService{
    private final IncidenciaRepository incidenciaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;
    

    public IncidenciaResponseDto crearIncidencia(IncidenciaRequestDto dto){
        Incidencia nuevaincidencia = new Incidencia();
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + dto.getJugadorId() + " no encontrado"));
        nuevaincidencia.setJugador(jugador);
        Partido partido = partidoRepository.findById(dto.getPartidoId())
            .orElseThrow(() -> new RecursoNoEncontradoException(("Partido con id " + dto.getPartidoId() + " no encontrado")));
        nuevaincidencia.setPartido(partido);
        nuevaincidencia.setFecha(dto.getFecha());
        nuevaincidencia.setMotivo(dto.getMotivo());
        nuevaincidencia.setCantidadDias(dto.getCantidadDias());
        return convertirDto(incidenciaRepository.save(nuevaincidencia));
    }

    public List<IncidenciaResponseDto> obtenerIncidencias(){
        List<Incidencia> incidencias = incidenciaRepository.findAll();
        return incidencias.stream().map(incidencia ->{
            return convertirDto(incidencia);
        }).toList();
    }

    public IncidenciaResponseDto obtenerIncidencia(Long id){
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Incidencia con id " + id + " no encontrado"));
        return convertirDto(incidencia);
    }

    public void eliminarIncidencia(Long id){
        incidenciaRepository.deleteById(id);
    }

    @Override
    public IncidenciaResponseDto actualizarIncidencia(Long id, IncidenciaPutDto dto){
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Incidencia con id " + id + " no encontrada"));
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + dto.getJugadorId() + " no encontrado"));
        incidencia.setJugador(jugador);
        Partido partido = partidoRepository.findById(dto.getPartidoId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + dto.getPartidoId() + " no encontrado"));
        incidencia.setPartido(partido);
        incidencia.setFecha(dto.getFecha());
        incidencia.setMotivo(dto.getMotivo());
        incidencia.setCantidadDias(dto.getCantidadDias());
        return convertirDto(incidenciaRepository.save(incidencia));
    }

    @Override
    public IncidenciaResponseDto actualizarDiasIncidencia(Long id, IncidenciaPatchDto dto){
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Incidencia con id " + id + " no encontrada"));
        if(dto.getCantidadDias() != null){
            incidencia.setCantidadDias(dto.getCantidadDias());
        }
        Incidencia incidenciaActualizada = incidenciaRepository.save(incidencia);
        IncidenciaResponseDto respuesta = new IncidenciaResponseDto();
        respuesta.setId(incidenciaActualizada.getId());
        respuesta.setCantidadDias(incidenciaActualizada.getCantidadDias());
        return respuesta;
    }

    private IncidenciaResponseDto convertirDto(Incidencia i){
        IncidenciaResponseDto dto = new IncidenciaResponseDto();
        dto.setId(i.getId());
        if(i.getJugador() != null){
            dto.setJugadorId(i.getJugador().getId());
        }
        if(i.getPartido() != null){
            dto.setPartidoId(i.getPartido().getId());
        }
        dto.setFecha(i.getFecha());
        dto.setMotivo(i.getMotivo());
        dto.setCantidadDias(i.getCantidadDias());
        return dto;
    }


}

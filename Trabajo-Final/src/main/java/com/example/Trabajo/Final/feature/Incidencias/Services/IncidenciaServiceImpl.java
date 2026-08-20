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
        Incidencia guardado = incidenciaRepository.save(nuevaincidencia);

        IncidenciaResponseDto respuesta = new IncidenciaResponseDto();
        respuesta.setId(guardado.getId());
        if(guardado.getJugador() != null){
            respuesta.setJugadorId(guardado.getJugador().getId());
        }
        if (guardado.getPartido() != null) {
            respuesta.setPartidoId(guardado.getPartido().getId());
        }
        respuesta.setFecha(guardado.getFecha());
        respuesta.setMotivo(guardado.getMotivo());
        respuesta.setCantidadDias(guardado.getCantidadDias());
        return respuesta;
    }

    public List<IncidenciaResponseDto> obtenerIncidencias(){
        List<Incidencia> incidencias = incidenciaRepository.findAll();
        return incidencias.stream().map(incidencia ->{
            IncidenciaResponseDto respuesta = new IncidenciaResponseDto();
            respuesta.setId(incidencia.getId());
            if (incidencia.getJugador() != null) {
                respuesta.setJugadorId(incidencia.getJugador().getId());
            }
            if(incidencia.getPartido() != null){
                respuesta.setPartidoId(incidencia.getPartido().getId());
            }
            respuesta.setFecha(incidencia.getFecha());
            respuesta.setMotivo(incidencia.getMotivo());
            respuesta.setCantidadDias(incidencia.getCantidadDias());
            return respuesta;
        }).toList();
    }

    public IncidenciaResponseDto obtenerIncidencia(Long id){
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Incidencia con id " + id + " no encontrado"));
        IncidenciaResponseDto respuesta = new IncidenciaResponseDto();
        respuesta.setId(incidencia.getId());
        if(incidencia.getJugador() != null){
            respuesta.setJugadorId(incidencia.getJugador().getId());
        }
        if(incidencia.getPartido() != null){
            respuesta.setPartidoId(incidencia.getPartido().getId());
        }
        respuesta.setFecha(incidencia.getFecha());
        respuesta.setMotivo(incidencia.getMotivo());
        respuesta.setCantidadDias(incidencia.getCantidadDias());
        return respuesta;
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
        Incidencia incidenciaActualizada = incidenciaRepository.save(incidencia);

        IncidenciaResponseDto respuesta = new IncidenciaResponseDto();
        respuesta.setId(incidenciaActualizada.getId());
        if(incidenciaActualizada.getJugador() != null){
            respuesta.setJugadorId(incidenciaActualizada.getJugador().getId());
        }
        if(incidenciaActualizada.getPartido() != null){
            respuesta.setPartidoId(incidenciaActualizada.getPartido().getId());
        }
        respuesta.setFecha(incidenciaActualizada.getFecha());
        respuesta.setMotivo(incidenciaActualizada.getMotivo());
        respuesta.setCantidadDias(incidenciaActualizada.getCantidadDias());
        return respuesta;
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


}

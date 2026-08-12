package com.example.Trabajo.Final.feature.Partido.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;
import com.example.Trabajo.Final.feature.Campeonato.Repositories.CampeonatoRepository;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoRequestDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl {
    private final PartidoRepository partidoRepository;
    private final CategoriaRepository categoriaRepository;
   private final CampeonatoRepository campeonatoRepository;
   private final EstadisticaRepository estadisticaRepository;
   private final JugadorRepository jugadorRepository;

   public PartidoResponseDto crearPartido(PartidoRequestDto dto){
    Partido nuevoPartido = new Partido();
    nuevoPartido.setJornada(dto.getJornada());
    nuevoPartido.setFecha(dto.getFecha());
    nuevoPartido.setRival(dto.getRival());
    Campeonato campeonato = campeonatoRepository.findById(dto.getCampeonatoId())
        .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));
    nuevoPartido.setCampeonato(campeonato);
    nuevoPartido.setResultado(dto.getResultado());
    nuevoPartido.setRival(dto.getRival());
    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() ->  new RuntimeException("Categoria no encontrada"));
    nuevoPartido.setCategoria(categoria);
    Partido guardado = partidoRepository.save(nuevoPartido);
    if (dto.getEstadisticas() != null) {

    for (EstadisticaRequestDto estadisticaDto : dto.getEstadisticas()) {

        Jugador jugador = jugadorRepository.findById(estadisticaDto.getJugadorId())
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        Estadistica estadistica = new Estadistica();

        estadistica.setJugador(jugador);
        estadistica.setPartido(guardado);
        estadistica.setMinutos(estadisticaDto.getMinutos());
        estadistica.setGoles(estadisticaDto.getGoles());
        estadistica.setAsistencias(estadisticaDto.getAsistencias());
        estadistica.setRojas(estadisticaDto.getRojas());
        estadistica.setAmarillas(estadisticaDto.getAmarillas());
        estadistica.setTitular(estadisticaDto.getTitular());

        estadisticaRepository.save(estadistica);
    }
}

    PartidoResponseDto respuesta = new PartidoResponseDto();
    respuesta.setId(guardado.getId());
    respuesta.setJornada(guardado.getJornada());
    respuesta.setFecha(guardado.getFecha());
    if(guardado.getCampeonato() != null){
        respuesta.setCampeonatoId(guardado.getCampeonato().getId());
    }
    respuesta.setResultado(guardado.getResultado());
    respuesta.setLocal(guardado.getLocal());
    if(guardado.getCategoria() != null){
       respuesta.setCategoriaId(guardado.getCategoria().getId());
    }
    return respuesta;
    }

    public List<PartidoResponseDto> obtenerPartidos() {

    List<Partido> partidos = partidoRepository.findAll();

    return partidos.stream().map(partido -> {

        PartidoResponseDto respuesta = new PartidoResponseDto();

        respuesta.setId(partido.getId());
        respuesta.setJornada(partido.getJornada());
        respuesta.setFecha(partido.getFecha());
        respuesta.setRival(partido.getRival());
        respuesta.setResultado(partido.getResultado());
        respuesta.setLocal(partido.getLocal());

        if (partido.getCategoria() != null) {
            respuesta.setCategoriaId(partido.getCategoria().getId());
        }

        if (partido.getCampeonato() != null) {
            respuesta.setCampeonatoId(partido.getCampeonato().getId());
        }

        return respuesta;

    }).toList();
}



    public PartidoResponseDto obtenerPartido(Long id){
        Partido partido = partidoRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        PartidoResponseDto respuesta = new PartidoResponseDto();
        respuesta.setId(partido.getId());
        respuesta.setJornada(partido.getJornada());
        respuesta.setFecha(partido.getFecha());
        respuesta.setRival(partido.getRival());
        if(partido.getCampeonato() != null){
        respuesta.setCampeonatoId(partido.getCampeonato().getId());
        }
        respuesta.setResultado(partido.getResultado());
        respuesta.setLocal(partido.getLocal());
        if(partido.getCategoria() != null){
        respuesta.setCategoriaId(partido.getCategoria().getId());
        }
        return respuesta;
    }

    public void eliminarPartido(Long id){
        partidoRepository.deleteById(id);
    }
}



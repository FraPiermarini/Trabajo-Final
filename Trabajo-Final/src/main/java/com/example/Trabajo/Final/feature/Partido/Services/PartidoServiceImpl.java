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
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPatchDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoPutDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoRequestDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;
import com.example.Trabajo.Final.feature.Partido.Services.Interface.PartidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartidoServiceImpl implements PartidoService{
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
        .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato con id " + dto.getCampeonatoId() + " no encontrado"));
    nuevoPartido.setCampeonato(campeonato);
    nuevoPartido.setResultado(dto.getResultado());
    nuevoPartido.setRival(dto.getRival());
    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() ->  new RecursoNoEncontradoException("Categoria con id " + dto.getCategoriaId() + " no encontrada"));
    nuevoPartido.setCategoria(categoria);
    Partido guardado = partidoRepository.save(nuevoPartido);
    if (dto.getEstadisticas() != null) {

    for (EstadisticaRequestDto estadisticaDto : dto.getEstadisticas()) {

        Jugador jugador = jugadorRepository.findById(estadisticaDto.getJugadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id" + estadisticaDto.getJugadorId() + " no encontrado"));

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
           .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + id + " no encontrado"));
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

    @Override
    public PartidoResponseDto actualizarPartido(Long id, PartidoPutDto dto){
        Partido partido = partidoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + id + " no encontrado"));
        partido.setFecha(dto.getFecha());
        partido.setJornada(dto.getJornada());
        partido.setRival(dto.getRival());
        Campeonato campeonato = campeonatoRepository.findById(dto.getCampeonatoId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato con id " + dto.getCampeonatoId() + " no encontrado"));
        partido.setCampeonato(campeonato);
        partido.setResultado(dto.getResultado());
        partido.setLocal(dto.getLocal());
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() ->  new RecursoNoEncontradoException("Categoria con id " + dto.getCategoriaId() + " no encontrada"));
        partido.setCategoria(categoria);
        if (dto.getEstadisticas() != null) {

        for (EstadisticaRequestDto estadisticaDto : dto.getEstadisticas()) {

            Estadistica estadistica = estadisticaRepository
                    .findByJugadorIdAndPartidoId(
                            estadisticaDto.getJugadorId(),
                            partido.getId()
                    )
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "Estadística del jugador con id "
                            + estadisticaDto.getJugadorId()
                            + " no encontrada para este partido"
                    ));

            estadistica.setMinutos(estadisticaDto.getMinutos());
            estadistica.setGoles(estadisticaDto.getGoles());
            estadistica.setAsistencias(estadisticaDto.getAsistencias());
            estadistica.setRojas(estadisticaDto.getRojas());
            estadistica.setAmarillas(estadisticaDto.getAmarillas());
            estadistica.setTitular(estadisticaDto.getTitular());

            estadisticaRepository.save(estadistica);
        }
    }
    Partido actualizado = partidoRepository.save(partido);
    PartidoResponseDto respuesta = new PartidoResponseDto();

    respuesta.setId(actualizado.getId());
    respuesta.setJornada(actualizado.getJornada());
    respuesta.setFecha(actualizado.getFecha());
    respuesta.setRival(actualizado.getRival());
    respuesta.setResultado(actualizado.getResultado());
    respuesta.setLocal(actualizado.getLocal());

    if (actualizado.getCampeonato() != null) {
        respuesta.setCampeonatoId(actualizado.getCampeonato().getId());
    }

    if (actualizado.getCategoria() != null) {
        respuesta.setCategoriaId(actualizado.getCategoria().getId());
    }

    return respuesta;
}

  @Override 
  public PartidoResponseDto actualizarResultadoPartido(Long id, PartidoPatchDto dto){
    Partido partido = partidoRepository.findById(id)
        .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + id + " no encontrado"));
    if(dto.getResultado() != null){
        partido.setResultado(dto.getResultado());
    }
    Partido partidoActualizado = partidoRepository.save(partido);

    PartidoResponseDto respuesta = new PartidoResponseDto();
    respuesta.setId(partidoActualizado.getId());
    respuesta.setResultado(partidoActualizado.getResultado());
    return respuesta;
  }

}



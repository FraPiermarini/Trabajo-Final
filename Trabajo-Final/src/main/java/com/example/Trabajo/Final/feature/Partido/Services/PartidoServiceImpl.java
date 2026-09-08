package com.example.Trabajo.Final.feature.Partido.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;
import com.example.Trabajo.Final.feature.Campeonato.Repositories.CampeonatoRepository;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Entrenador.Repositories.EntrenadorRepository;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
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
   private final EntrenadorRepository entrenadorRepository;

   public PartidoResponseDto crearPartido(PartidoRequestDto dto){
    Partido nuevoPartido = new Partido();
    nuevoPartido.setJornada(dto.getJornada());
    nuevoPartido.setFecha(dto.getFecha());
    nuevoPartido.setRival(dto.getRival());
    Campeonato campeonato = campeonatoRepository.findById(dto.getCampeonatoId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato con id " + dto.getCampeonatoId() + " no encontrado"));
    nuevoPartido.setCampeonato(campeonato);
    nuevoPartido.setResultado(dto.getResultado());
    nuevoPartido.setLocal(dto.getLocal());
    Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
        .orElseThrow(() ->  new RecursoNoEncontradoException("Categoria con id " + dto.getCategoriaId() + " no encontrada"));
    nuevoPartido.setCategoria(categoria);
    Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + dto.getEntrenadorId() + " no encontrado" ));
    nuevoPartido.setEntrenador(entrenador);
    Partido guardado = partidoRepository.save(nuevoPartido);
    return convertirDto(guardado);
    }

    public List<PartidoResponseDto> obtenerPartidos() {
    List<Partido> partidos = partidoRepository.findAll();
    return partidos.stream().map(partido -> {

        return convertirDto(partido);

    }).toList();
    }



    public PartidoResponseDto obtenerPartido(Long id){
        Partido partido = partidoRepository.findById(id)
           .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + id + " no encontrado"));
        return convertirDto(partido);
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
        Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
        .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + dto.getEntrenadorId() + " no encontrado" ));
        partido.setEntrenador(entrenador);
    return convertirDto(partidoRepository.save(partido));
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

  private PartidoResponseDto convertirDto(Partido p){
    PartidoResponseDto dto = new PartidoResponseDto();
    dto.setId(p.getId());
    dto.setJornada(p.getJornada());
    dto.setFecha(p.getFecha());
    dto.setRival(p.getRival());
    dto.setResultado(p.getResultado());
    dto.setLocal(p.getLocal());
    if(p.getCategoria() != null){
        dto.setCategoriaId(p.getCategoria().getId());
    }
    if(p.getCampeonato() != null){
        dto.setCampeonatoId(p.getCampeonato().getId());
    }
    if(p.getCampeonato() != null){
        dto.setEntrenadorId(p.getEntrenador().getId());
    }
    return dto;
  }

}



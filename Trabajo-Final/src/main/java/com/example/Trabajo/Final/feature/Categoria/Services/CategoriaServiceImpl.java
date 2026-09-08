package com.example.Trabajo.Final.feature.Categoria.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Categoria.Dtos.CategoriaDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaPutDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaRequestDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Categoria.Services.Interface.CategoriaService;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Entrenador.Repositories.EntrenadorRepository;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Jugador.Dtos.JugadorDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{
    private final CategoriaRepository categoriaRepository;
    private final EntrenadorRepository entrenadorRepository;

    public CategoriaResponseDto crearCategoria(CategoriaRequestDto dto){
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(dto.getNombre());
        nuevaCategoria.setAño(dto.getAño());
        nuevaCategoria.setDescripcion(dto.getDescripcion());
        if(dto.getEntrenadorId() != null){
        Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + dto.getEntrenadorId() + " no encontrado"));
        nuevaCategoria.setEntrenador(entrenador); 
        }
        return convertirDto(categoriaRepository.save(nuevaCategoria));
    }

    public List<CategoriaResponseDto> obtenerCategorias(){
        List<Categoria> categorias =  categoriaRepository.findAll();
        return categorias.stream().map(categoria ->{
            return convertirDto(categoria);
        }).toList();
    }


    public CategoriaResponseDto obtenerCategoria(Long id){
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Categoría con id " + id + " no encontrada"));
        return convertirDto(categoria);
    } 
    
     public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaResponseDto actualizarCategoria(Long id, CategoriaPutDto dto){
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Categoria con id " + id + " no encontrada"));
        categoria.setNombre(dto.getNombre());
        categoria.setAño(dto.getAño());
        categoria.setDescripcion(dto.getDescripcion());
        Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + dto.getEntrenadorId() + " no encontrado"));
        categoria.setEntrenador(entrenador);
        return convertirDto(categoriaRepository.save(categoria));
    }

    private CategoriaResponseDto convertirDto(Categoria c){
        CategoriaResponseDto dto = new CategoriaResponseDto();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setAño(c.getAño());
        dto.setDescripcion(c.getDescripcion());
        if(c.getEntrenador() != null){
            dto.setEntrenadorId(c.getEntrenador().getId());
            dto.setNombreEntrenador(c.getEntrenador().getNombre());
        }
        List<JugadorDto> jugadores = new ArrayList<>();
        for(Jugador jugador : c.getJugadores()){
            JugadorDto jugadorDto = new JugadorDto();
            jugadorDto.setId(jugador.getId());
            jugadorDto.setNombre(jugador.getNombre());
            jugadorDto.setApellido(jugador.getApellido());
            jugadorDto.setPosicion(jugador.getPosicion());
            jugadorDto.setNumeroCamiseta(jugador.getNumeroCamiseta());
            jugadores.add(jugadorDto);
        }
        dto.setJugadores(jugadores);
        return dto;
    }

}



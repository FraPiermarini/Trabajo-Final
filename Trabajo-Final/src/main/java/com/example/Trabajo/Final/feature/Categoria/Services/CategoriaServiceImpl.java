package com.example.Trabajo.Final.feature.Categoria.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Categoria.Dtos.CategoriaDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaRequestDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Entrenador.Repositories.EntrenadorRepository;
import com.example.Trabajo.Final.feature.Jugador.Dtos.JugadorDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl {
    private final CategoriaRepository categoriaRepository;
    private final EntrenadorRepository entrenadorRepository;

    public CategoriaResponseDto crearCategoria(CategoriaRequestDto dto){
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(dto.getNombre());
        nuevaCategoria.setAño(dto.getAño());
        nuevaCategoria.setDescripcion(dto.getDescripcion());
        Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
        nuevaCategoria.setEntrenador(entrenador); 
        Categoria guardado = categoriaRepository.save(nuevaCategoria);

        CategoriaResponseDto respuesta = new CategoriaResponseDto();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setAño(guardado.getAño());
        respuesta.setDescripcion(guardado.getDescripcion());
        if (guardado.getEntrenador() != null) {
        respuesta.setEntrenadorId(guardado.getEntrenador().getId());
    }
        return respuesta;
    }

    public List<CategoriaResponseDto> obtenerCategorias(){
        List<Categoria> categorias =  categoriaRepository.findAll();
        return categorias.stream().map(categoria ->{
            CategoriaResponseDto respuesta = new CategoriaResponseDto();
            respuesta.setId(categoria.getId());
            respuesta.setNombre(categoria.getNombre());
            respuesta.setAño(categoria.getAño());
            respuesta.setDescripcion(categoria.getDescripcion());
            if(categoria.getEntrenador() != null){
                respuesta.setEntrenadorId(categoria.getEntrenador().getId());
                respuesta.setNombreEntrenador(categoria.getEntrenador().getNombre());
            }
            List<JugadorDto> jugadores = new ArrayList<>();
            for (Jugador jugador : categoria.getJugadores()) {
                JugadorDto dto = new JugadorDto();
                dto.setId(jugador.getId());
                dto.setNombre(jugador.getNombre());
                dto.setApellido(jugador.getApellido());
                dto.setPosicion(jugador.getPosicion());
                dto.setNumeroCamiseta(jugador.getNumeroCamiseta());
                jugadores.add(dto);
            }
            respuesta.setJugadores(jugadores);
            return respuesta;
        }).toList();
    }


    public CategoriaResponseDto obtenerCategoria(Long id){
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        CategoriaResponseDto respuesta = new CategoriaResponseDto();
        respuesta.setId(categoria.getId());
        respuesta.setNombre(categoria.getNombre());
        respuesta.setAño(categoria.getAño());
        respuesta.setDescripcion(categoria.getDescripcion());
        if (categoria.getEntrenador() != null) {
        respuesta.setEntrenadorId(categoria.getEntrenador().getId());
        respuesta.setNombreEntrenador(categoria.getEntrenador().getNombre());
        }
        List<JugadorDto> jugadores = new ArrayList<>();
        for (Jugador jugador : categoria.getJugadores()) {
            JugadorDto dto = new JugadorDto();
            dto.setId(jugador.getId());
            dto.setNombre(jugador.getNombre());
            dto.setApellido(jugador.getApellido());
            dto.setPosicion(jugador.getPosicion());
            dto.setNumeroCamiseta(jugador.getNumeroCamiseta());
            jugadores.add(dto);
        }
        respuesta.setJugadores(jugadores);
        return respuesta;
    } 
    
     public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

}



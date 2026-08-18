package com.example.Trabajo.Final.feature.Entrenador.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Categoria.Dtos.CategoriaDto;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Request.EntrenadorPutDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Request.EntrenadorRequestDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Response.EntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;
import com.example.Trabajo.Final.feature.Entrenador.Repositories.EntrenadorRepository;
import com.example.Trabajo.Final.feature.Entrenador.Services.Interface.EntrenadorService;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrenadorServiceImpl implements EntrenadorService{
    
    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorResponseDto crearEntrenador(EntrenadorRequestDto dto){
        Entrenador nuevoEntrenador = new Entrenador();
        nuevoEntrenador.setNombre(dto.getNombre());
        nuevoEntrenador.setApellido(dto.getApellido());
        nuevoEntrenador.setEdad(dto.getEdad());
        Entrenador guardado = entrenadorRepository.save(nuevoEntrenador);

        EntrenadorResponseDto respuesta = new EntrenadorResponseDto();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setApellido(guardado.getApellido());
        respuesta.setEdad(guardado.getEdad());
        return respuesta;
    }

    public List<EntrenadorResponseDto> obtenerEntrenadores(){
        List<Entrenador> entrenadores = entrenadorRepository.findAll();
        return entrenadores.stream().map(entrenador -> {
            EntrenadorResponseDto respuesta = new EntrenadorResponseDto();
            respuesta.setId(entrenador.getId());
            respuesta.setNombre(entrenador.getNombre());
            respuesta.setApellido(entrenador.getApellido());
            respuesta.setEdad(entrenador.getEdad());
            List<CategoriaDto> categorias = new ArrayList<>();
            for (Categoria categoria : entrenador.getCategorias()) {
                CategoriaDto dto = new CategoriaDto();
                dto.setId(categoria.getId());
                dto.setNombre(categoria.getNombre());
                categorias.add(dto);
            }
            respuesta.setCategorias(categorias);
                return respuesta;
        }).toList();
    }

    public EntrenadorResponseDto obtenerEntrenador(Long id){
        Entrenador entrenador = entrenadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + id + " no encontrado"));
        
        EntrenadorResponseDto respuesta = new EntrenadorResponseDto();
        respuesta.setId((entrenador.getId()));
        respuesta.setNombre(entrenador.getNombre());
        respuesta.setApellido(entrenador.getApellido());
        respuesta.setEdad(entrenador.getEdad());
        List<CategoriaDto> categorias = new ArrayList<>();
        for (Categoria categoria : entrenador.getCategorias()) {
            CategoriaDto dto = new CategoriaDto();
            dto.setId(categoria.getId());
            dto.setNombre(categoria.getNombre());
            categorias.add(dto);
        }
        respuesta.setCategorias(categorias);
        return respuesta;
    }
    
    public void eliminarEntrenador(Long id){
        entrenadorRepository.deleteById(id);
    }
    
    @Override
    public EntrenadorResponseDto actualizarEntrenador(Long id, EntrenadorPutDto dto){
        Entrenador entrenador = entrenadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + id + " no encontrado"));
        entrenador.setNombre(dto.getNombre());
        entrenador.setApellido(dto.getApellido());
        entrenador.setEdad(dto.getEdad());
        Entrenador entrenadorActualizado = entrenadorRepository.save(entrenador);

        EntrenadorResponseDto respuesta = new EntrenadorResponseDto();
        respuesta.setId(entrenadorActualizado.getId());
        respuesta.setNombre(entrenadorActualizado.getNombre());
        respuesta.setApellido(entrenadorActualizado.getApellido());
        respuesta.setEdad(entrenadorActualizado.getEdad());
        List<CategoriaDto> categorias = new ArrayList<>();
        for (Categoria categoria : entrenador.getCategorias()) {
            CategoriaDto categoriadto = new CategoriaDto();
            categoriadto.setId(categoria.getId());
            categoriadto.setNombre(categoria.getNombre());
            categorias.add(categoriadto);
        }
        respuesta.setCategorias(categorias);
        return respuesta;
    }
}







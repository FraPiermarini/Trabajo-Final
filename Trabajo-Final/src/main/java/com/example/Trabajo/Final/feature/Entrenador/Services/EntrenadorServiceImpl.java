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
        return convertirDto(entrenadorRepository.save(nuevoEntrenador));
    }

    public List<EntrenadorResponseDto> obtenerEntrenadores(){
        List<Entrenador> entrenadores = entrenadorRepository.findAll();
        return entrenadores.stream().map(entrenador -> {
            return convertirDto(entrenador);
        }).toList();
    }

    public EntrenadorResponseDto obtenerEntrenador(Long id){
        Entrenador entrenador = entrenadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Entrenador con id " + id + " no encontrado"));
        
        return convertirDto(entrenador);
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
        return convertirDto(entrenadorRepository.save(entrenador));
    }

    private EntrenadorResponseDto convertirDto(Entrenador e){
        EntrenadorResponseDto dto = new EntrenadorResponseDto();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setEdad(e.getEdad());
        List<CategoriaDto> categorias = new ArrayList<>();
        for(Categoria categoria : e.getCategorias()){
            CategoriaDto categoriadto = new CategoriaDto();
            categoriadto.setId(categoria.getId());
            categoriadto.setNombre(categoria.getNombre());
            categorias.add(categoriadto);
        }
        dto.setCategorias(categorias);
        return dto;
    }
}







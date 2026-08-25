package com.example.Trabajo.Final.feature.Jugador.Services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPatchDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorPutDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorRequestDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Jugador.Services.Interface.JugadorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService{
    private final JugadorRepository jugadorRepository;
    private final CategoriaRepository categoriaRepository;

    public JugadorResponseDto crearJugador(JugadorRequestDto dto){
        Jugador nuevojugador = new Jugador();
        nuevojugador.setNombre(dto.getNombre());
        nuevojugador.setApellido(dto.getApellido());
        nuevojugador.setDni(dto.getDni());
        nuevojugador.setFechaNacimiento(dto.getFechaNacimiento());
        nuevojugador.setPosicion(dto.getPosicion());
        nuevojugador.setNumeroCamiseta(dto.getNumeroCamiseta());
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Categoria con id " + dto.getCategoriaId() + " no encontrado"));
        nuevojugador.setCategoria(categoria);
        return convertirDto(jugadorRepository.save(nuevojugador));
    }

    public List<JugadorResponseDto> obtenerJugadores(){
        List<Jugador> jugadores = jugadorRepository.findAll();
        return jugadores.stream().map(jugador ->{
            return convertirDto(jugador);
        }).toList();
    }

    public JugadorResponseDto obtenerJugador(Long id){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + id + " no encontrado"));
        return convertirDto(jugador);
    }

    public void eliminarJugador(Long id){
        jugadorRepository.deleteById(id);
    }

    @Override
    public JugadorResponseDto actualizarJugador(Long id, JugadorPutDto dto){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + id + " no encontrado"));
        jugador.setNombre(dto.getNombre());
        jugador.setApellido(dto.getApellido());
        jugador.setDni(dto.getDni());
        jugador.setFechaNacimiento(dto.getFechaNacimiento());
        jugador.setPosicion(dto.getPosicion());
        jugador.setNumeroCamiseta(dto.getNumeroCamiseta());
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Categoria con id " + dto.getCategoriaId() + " no encontrado"));
        jugador.setCategoria(categoria);
        return convertirDto(jugadorRepository.save(jugador));
    }

    @Override
    public JugadorResponseDto actualizarNumeroJugador(Long id, JugadorPatchDto dto){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + id + " no encontrado"));
        if(dto.getNumeroCamiseta() != null){
            jugador.setNumeroCamiseta(dto.getNumeroCamiseta());
        }
        Jugador jugadorActualizado = jugadorRepository.save(jugador);

        JugadorResponseDto respuesta = new JugadorResponseDto();
        respuesta.setId(jugadorActualizado.getId());
        respuesta.setNumeroCamiseta(jugadorActualizado.getNumeroCamiseta());
        return respuesta;
    }

    public JugadorResponseDto importarImagen(Long id, MultipartFile imagen){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + id + " no encontrado"));
        if(jugador.getImagenUrl() != null && !jugador.getImagenUrl().isBlank()){
            throw new RuntimeException("El jugador ya tiene una imagen asignada");
        }
        try{
            String base64 = Base64.getEncoder().encodeToString(imagen.getBytes());
            String mediaType = imagen.getContentType();
            jugador.setImagenUrl("data:" + mediaType + ";base64," + base64);
        }catch(IOException e){
            throw new RuntimeException("Error al procesar la imagen");
        }
        return convertirDto(jugadorRepository.save(jugador));
    }

    private JugadorResponseDto convertirDto(Jugador j){
        JugadorResponseDto dto = new JugadorResponseDto();
        dto.setId(j.getId());
        dto.setNombre(j.getNombre());
        dto.setApellido(j.getApellido());
        dto.setDni(j.getDni());
        dto.setFechaNacimiento(j.getFechaNacimiento());
        dto.setPosicion(j.getPosicion());
        dto.setNumeroCamiseta(j.getNumeroCamiseta());
        if(j.getCategoria() != null){
            dto.setCategoriaId(j.getCategoria().getId());
            dto.setNombreCategoria(j.getCategoria().getNombre());
        }
        dto.setImagenUrl(j.getImagenUrl());
        return dto;
    }
}

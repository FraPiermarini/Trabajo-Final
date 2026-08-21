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
        Jugador guardado = jugadorRepository.save(nuevojugador);

        JugadorResponseDto respuesta = new JugadorResponseDto();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setApellido(guardado.getApellido());
        respuesta.setDni(guardado.getDni());
        respuesta.setFechaNacimiento(guardado.getFechaNacimiento());
        respuesta.setPosicion(guardado.getPosicion());
        respuesta.setNumeroCamiseta(guardado.getNumeroCamiseta());
        if (guardado.getCategoria() != null) {
            respuesta.setCategoriaId(guardado.getCategoria().getId());
        }
        respuesta.setImagenUrl(guardado.getImagenUrl());
        return respuesta;
    }

    public List<JugadorResponseDto> obtenerJugadores(){
        List<Jugador> jugadores = jugadorRepository.findAll();
        return jugadores.stream().map(jugador ->{
            JugadorResponseDto respuesta = new JugadorResponseDto();
            respuesta.setId(jugador.getId());
            respuesta.setNombre(jugador.getNombre());
            respuesta.setApellido(jugador.getApellido());
            respuesta.setDni(jugador.getDni());
            respuesta.setFechaNacimiento(jugador.getFechaNacimiento());
            respuesta.setPosicion(jugador.getPosicion());
            respuesta.setNumeroCamiseta(jugador.getNumeroCamiseta());
            if(jugador.getCategoria() != null){
                respuesta.setCategoriaId(jugador.getCategoria().getId());
                respuesta.setNombreCategoria(jugador.getCategoria().getNombre());
            }
            respuesta.setImagenUrl(jugador.getImagenUrl());
            return respuesta;

        }).toList();
    }

    public JugadorResponseDto obtenerJugador(Long id){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + id + " no encontrado"));
        JugadorResponseDto respuesta = new JugadorResponseDto();
        respuesta.setId(jugador.getId());
        respuesta.setNombre(jugador.getNombre());
        respuesta.setApellido(jugador.getApellido());
        respuesta.setDni(jugador.getDni());
        respuesta.setFechaNacimiento(jugador.getFechaNacimiento());
        respuesta.setPosicion(jugador.getPosicion());
        respuesta.setNumeroCamiseta(jugador.getNumeroCamiseta());
        if (jugador.getCategoria() != null) {
            respuesta.setCategoriaId(jugador.getCategoria().getId());
            respuesta.setNombreCategoria(jugador.getCategoria().getNombre());
        }
        respuesta.setImagenUrl(jugador.getImagenUrl());
        return respuesta;
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
        Jugador jugadorActualizado = jugadorRepository.save(jugador);
        
        JugadorResponseDto  respuesta = new JugadorResponseDto();
        respuesta.setId(jugadorActualizado.getId());
        respuesta.setNombre(jugadorActualizado.getNombre());
        respuesta.setApellido(jugadorActualizado.getApellido());
        respuesta.setDni(jugadorActualizado.getDni());
        respuesta.setFechaNacimiento(jugadorActualizado.getFechaNacimiento());
        respuesta.setPosicion(jugadorActualizado.getPosicion());
        respuesta.setNumeroCamiseta(jugadorActualizado.getNumeroCamiseta());
        if (jugadorActualizado.getCategoria() != null) {
            respuesta.setCategoriaId(jugadorActualizado.getCategoria().getId());
            respuesta.setNombreCategoria(jugadorActualizado.getCategoria().getNombre());
        }
        respuesta.setImagenUrl(jugadorActualizado.getImagenUrl());
        return respuesta;
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
        Jugador guardado = jugadorRepository.save(jugador);
        JugadorResponseDto dto = new JugadorResponseDto();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setApellido(guardado.getApellido());
        dto.setDni(guardado.getDni());
        dto.setFechaNacimiento(guardado.getFechaNacimiento());
        dto.setPosicion(guardado.getPosicion());
        dto.setNumeroCamiseta(guardado.getNumeroCamiseta());
        dto.setCategoriaId(guardado.getCategoria().getId());
        dto.setImagenUrl(guardado.getImagenUrl());
        return dto;
    }
}

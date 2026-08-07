package com.example.Trabajo.Final.feature.Jugador.Services;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Request.JugadorRequestDto;
import com.example.Trabajo.Final.feature.Jugador.Dtos.Response.JugadorResponseDto;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl {
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
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        nuevojugador.setCategoria(categoria);
        Jugador guardado = jugadorRepository.save(nuevojugador);

        JugadorResponseDto respuesta = new JugadorResponseDto();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setApellido(guardado.getApellido());
        respuesta.setDni(guardado.getDni());
        respuesta.setFechaNaciemiento(guardado.getFechaNacimiento());
        respuesta.setPosicion(guardado.getPosicion());
        respuesta.setNumeroCamiseta(guardado.getNumeroCamiseta());
        if (guardado.getCategoria() != null) {
            respuesta.setCategoriaId(guardado.getCategoria().getId());
        }
        return respuesta;
    }

    public JugadorResponseDto obtenerJugador(Long id){
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        JugadorResponseDto respuesta = new JugadorResponseDto();
        respuesta.setId(jugador.getId());
        respuesta.setNombre(jugador.getNombre());
        respuesta.setApellido(jugador.getApellido());
        respuesta.setDni(jugador.getDni());
        respuesta.setFechaNaciemiento(jugador.getFechaNacimiento());
        respuesta.setPosicion(jugador.getPosicion());
        respuesta.setNumeroCamiseta(jugador.getNumeroCamiseta());
        if (jugador.getCategoria() != null) {
            respuesta.setCategoriaId(jugador.getCategoria().getId());
            respuesta.setNombreCategoria(jugador.getCategoria().getNombre());
        }
        return respuesta;
    }

    public void eliminarJugador(Long id){
        jugadorRepository.deleteById(id);
    }
}

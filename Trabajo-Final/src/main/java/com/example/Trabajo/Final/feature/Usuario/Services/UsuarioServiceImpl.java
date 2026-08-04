package com.example.Trabajo.Final.feature.Usuario.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Usuario.Dtos.Request.RegistroRequestDto;
import com.example.Trabajo.Final.feature.Usuario.Dtos.Response.RegistroResponseDto;
import com.example.Trabajo.Final.feature.Usuario.Models.Usuario;
import com.example.Trabajo.Final.feature.Usuario.Repositories.UsuarioRepository;


@Service
public class UsuarioServiceImpl {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistroResponseDto registrarUsuario(RegistroRequestDto registroRequestDto){
        if (usuarioRepository.existsByEmail(registroRequestDto.getEmail())){
            throw new RuntimeException("El email ya esta registrado en el sistema");
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroRequestDto.getNombre());
        nuevoUsuario.setApellido(registroRequestDto.getApellido());
        nuevoUsuario.setEmail(registroRequestDto.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(registroRequestDto.getPassword()));
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        RegistroResponseDto respuesta = new RegistroResponseDto();
        respuesta.setId(usuarioGuardado.getId());
        respuesta.setNombre(usuarioGuardado.getNombre());
        respuesta.setApellido(usuarioGuardado.getApellido());
        respuesta.setEmail(usuarioGuardado.getEmail());
        return respuesta;
    }
    public RegistroResponseDto obtenerUsuarioPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            RegistroResponseDto respuesta = new RegistroResponseDto();
            respuesta.setId(usuario.getId());
            respuesta.setNombre(usuario.getNombre());
            respuesta.setApellido(usuario.getApellido());
            respuesta.setEmail(usuario.getEmail());
            return respuesta;

        }else {
            return null;
        }
    }
}

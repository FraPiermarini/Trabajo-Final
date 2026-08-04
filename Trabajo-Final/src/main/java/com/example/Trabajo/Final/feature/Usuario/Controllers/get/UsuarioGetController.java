package com.example.Trabajo.Final.feature.Usuario.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Usuario.Dtos.Response.RegistroResponseDto;
import com.example.Trabajo.Final.feature.Usuario.Models.Usuario;
import com.example.Trabajo.Final.feature.Usuario.Repositories.UsuarioRepository;
import com.example.Trabajo.Final.feature.Usuario.Services.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioGetController {
    private final UsuarioServiceImpl UsuarioServiceImpl;
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroResponseDto> obtenerUsuario(@PathVariable Long id){
        RegistroResponseDto usuario = UsuarioServiceImpl.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

package com.example.Trabajo.Final.feature.Usuario.Controllers.put;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Usuario.Models.Rol;
import com.example.Trabajo.Final.feature.Usuario.Models.Usuario;
import com.example.Trabajo.Final.feature.Usuario.Repositories.UsuarioRepository;



@RestController 
@RequestMapping("/api/usuarios")
public class UsuarioPutController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PutMapping("/{id}/rol")

    public ResponseEntity<?> cambiarRol(@PathVariable Long id, @RequestBody  Map<String, String> body){

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {

            return ResponseEntity.notFound().build();

        }

        String rolTexto = body.get("rol");

        if (rolTexto == null) {

            return ResponseEntity.badRequest()
                    .body(Map.of("message", "El rol es requerido"));

        }

        try {

            Rol nuevoRol = Rol.valueOf(rolTexto.toUpperCase());

            usuario.setRol(nuevoRol);

            usuarioRepository.save(usuario);

            return ResponseEntity.ok(
                    Map.of(
                            "message", "Rol actualizado correctamente",
                            "rol", nuevoRol.name()
                    )
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Rol inválido"));

        }

    }
}


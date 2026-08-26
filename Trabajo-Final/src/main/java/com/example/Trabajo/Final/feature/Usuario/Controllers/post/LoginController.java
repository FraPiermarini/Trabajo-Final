package com.example.Trabajo.Final.feature.Usuario.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.Trabajo.Final.config.JwtService;
import com.example.Trabajo.Final.feature.Usuario.Dtos.Request.LoginRequestDto;
import com.example.Trabajo.Final.feature.Usuario.Dtos.Response.LoginResponseDto;
import com.example.Trabajo.Final.feature.Usuario.Models.Usuario;
import com.example.Trabajo.Final.feature.Usuario.Repositories.UsuarioRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description =  "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {

        Usuario usuario = usuarioRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        String token = jwtService.generarToken(
                usuario.getEmail(),
                usuario.getRol().name()
        );

        LoginResponseDto response = new LoginResponseDto(token);

        return ResponseEntity.ok(response);
    }
}
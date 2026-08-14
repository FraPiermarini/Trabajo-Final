package com.example.Trabajo.Final.feature.Usuario.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Usuario.Dtos.Request.RegistroRequestDto;
import com.example.Trabajo.Final.feature.Usuario.Dtos.Response.RegistroResponseDto;
import com.example.Trabajo.Final.feature.Usuario.Services.UsuarioServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioPostController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroRequestDto registroRequestDto){
        try{
            RegistroResponseDto respuesta = usuarioService.registrarUsuario(registroRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponseDto("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
    
    public static class ErrorResponseDto {
        public String mensaje;
        public int codigo;

    public ErrorResponseDto(String mensaje, int codigo){
        this.mensaje = mensaje;
        this.codigo = codigo;
    }
    public String getMensaje(){
        return mensaje;
    }
    public int getCodigo(){
        return codigo;
    }
    
        
    }
}

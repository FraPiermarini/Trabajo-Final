package com.example.Trabajo.Final.feature.Entrenador.Controladores.post;

import com.example.Trabajo.Final.feature.Categoria.Services.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaRequestDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Request.EntrenadorRequestDto;
import com.example.Trabajo.Final.feature.Entrenador.Dtos.Response.EntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Entrenador.Services.EntrenadorServiceImpl;

@RestController
@RequestMapping("/api/entrenadores")

public class EntrenadorPostController {
    @Autowired
    private EntrenadorServiceImpl entrenadorServiceImpl;

    @PostMapping()
    public EntrenadorResponseDto crearEntrenador(@RequestBody EntrenadorRequestDto dto ){
        return entrenadorServiceImpl.crearEntrenador(dto);
    }


}

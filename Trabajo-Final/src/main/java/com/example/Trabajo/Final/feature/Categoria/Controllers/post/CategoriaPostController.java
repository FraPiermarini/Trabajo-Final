package com.example.Trabajo.Final.feature.Categoria.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaRequestDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Categoria.Services.CategoriaServiceImpl;

@RestController
@RequestMapping("api/categorias")
public class CategoriaPostController {
    @Autowired
    private CategoriaServiceImpl categoriaServiceImpl;

    @PostMapping()
    public CategoriaResponseDto crearCategoria(@RequestBody CategoriaRequestDto dto){
        return categoriaServiceImpl.crearCategoria(dto);
    }
}

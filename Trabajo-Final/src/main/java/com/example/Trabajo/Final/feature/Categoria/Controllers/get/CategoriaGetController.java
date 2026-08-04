package com.example.Trabajo.Final.feature.Categoria.Controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
import com.example.Trabajo.Final.feature.Categoria.Repositories.CategoriaRepository;
import com.example.Trabajo.Final.feature.Categoria.Services.CategoriaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "¨*")
@RequiredArgsConstructor
public class CategoriaGetController {
    private final CategoriaServiceImpl categoriaServiceImpl;
    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias(){
        return ResponseEntity.ok(categoriaRepository.findAll());    
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> obtenerCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaServiceImpl.obtenerCategoria(id));
}
}

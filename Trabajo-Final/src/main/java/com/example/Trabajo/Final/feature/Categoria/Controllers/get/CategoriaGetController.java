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

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Categorias", description = "Operaciones relacionadas con categoria")
@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "¨*")
@RequiredArgsConstructor
public class CategoriaGetController {
    private final CategoriaServiceImpl categoriaServiceImpl;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> obtenerCategorias(){
        return ResponseEntity.ok(categoriaServiceImpl.obtenerCategorias());    
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> obtenerCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaServiceImpl.obtenerCategoria(id));
}
}

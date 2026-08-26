package com.example.Trabajo.Final.feature.Categoria.Controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaPutDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;
import com.example.Trabajo.Final.feature.Categoria.Services.Interface.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Categorias", description = "Operaciones relacionadas con categoria")
@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoriaPutController {
    private final CategoriaService categoriaService;

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaPutDto dto){
        CategoriaResponseDto respuesta = categoriaService.actualizarCategoria(id, dto);
        return ResponseEntity.ok(respuesta);
    }
}

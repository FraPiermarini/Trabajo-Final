package com.example.Trabajo.Final.feature.Categoria.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Categoria.Services.CategoriaServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Categorias", description = "Operaciones relacionadas con categoria")
@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoriaDeleteController {
    @Autowired
    private CategoriaServiceImpl categoriaServiceImpl;

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
            categoriaServiceImpl.eliminarCategoria(id);
            return ResponseEntity.noContent().build();
        }
}

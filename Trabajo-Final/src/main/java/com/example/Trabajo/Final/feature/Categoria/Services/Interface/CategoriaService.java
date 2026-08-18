package com.example.Trabajo.Final.feature.Categoria.Services.Interface;

import com.example.Trabajo.Final.feature.Categoria.Dtos.Request.CategoriaPutDto;
import com.example.Trabajo.Final.feature.Categoria.Dtos.Response.CategoriaResponseDto;

public interface CategoriaService {
    CategoriaResponseDto actualizarCategoria(Long id, CategoriaPutDto dto);
}

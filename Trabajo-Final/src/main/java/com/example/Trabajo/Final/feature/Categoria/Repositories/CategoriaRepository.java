package com.example.Trabajo.Final.feature.Categoria.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Categoria.Models.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}

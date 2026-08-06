package com.example.Trabajo.Final.feature.Entrenador.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Entrenador.Models.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long>{
    
}

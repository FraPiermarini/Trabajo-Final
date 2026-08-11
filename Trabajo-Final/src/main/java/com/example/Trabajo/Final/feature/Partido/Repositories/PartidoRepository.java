package com.example.Trabajo.Final.feature.Partido.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Partido.Models.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{
    
}

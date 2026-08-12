package com.example.Trabajo.Final.feature.Campeonato.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long>{
    
}

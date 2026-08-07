package com.example.Trabajo.Final.feature.Jugador.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
    
}

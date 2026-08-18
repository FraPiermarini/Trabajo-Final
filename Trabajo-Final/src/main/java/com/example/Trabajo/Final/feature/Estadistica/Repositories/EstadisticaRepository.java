package com.example.Trabajo.Final.feature.Estadistica.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;

@Repository
public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {
        Optional<Estadistica> findByJugadorIdAndPartidoId(Long jugadorId, Long partidoId);

}

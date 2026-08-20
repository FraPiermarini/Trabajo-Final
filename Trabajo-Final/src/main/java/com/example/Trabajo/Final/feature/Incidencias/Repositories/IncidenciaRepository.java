package com.example.Trabajo.Final.feature.Incidencias.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Incidencias.Models.Incidencia;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;


@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByJugadorId(Long jugadorId);
    List<Incidencia> findByJugadorIdAndPartidoId(Long jugadorId, Long partidoId);
    List<Incidencia> findByFecha(LocalDate inicio, LocalDate fin);
    List<Incidencia> findByPartidoId(Long partidoId);
}

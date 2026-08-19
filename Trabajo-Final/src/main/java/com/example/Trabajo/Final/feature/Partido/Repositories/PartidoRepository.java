package com.example.Trabajo.Final.feature.Partido.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Trabajo.Final.feature.Partido.Models.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{
     @Query("""
        SELECT p
        FROM Partido p
        WHERE p.fecha >= :inicio
        AND p.fecha <= :fin
    """)
    List<Partido> buscarPartidosEntreFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    List<Partido> findByCampeonatoId(Long campeonatoId);
}

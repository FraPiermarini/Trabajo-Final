package com.example.Trabajo.Final.feature.Campeonato.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoRequestDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;
import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;
import com.example.Trabajo.Final.feature.Campeonato.Repositories.CampeonatoRepository;
import com.example.Trabajo.Final.feature.Partido.Dtos.PartidoDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampeonatoServiceImpl {
    private final CampeonatoRepository campeonatoRepository;
    private final PartidoRepository partidoRepository;

    public CampeonatoResponseDto crearCampeonato(CampeonatoRequestDto dto){
        Campeonato nuevoCampeonato = new Campeonato();
        nuevoCampeonato.setNombre(dto.getNombre());
        nuevoCampeonato.setAño(dto.getAño());
        Campeonato guardado = campeonatoRepository.save(nuevoCampeonato);

        CampeonatoResponseDto respuesta = new CampeonatoResponseDto();
        respuesta.setId(guardado.getId());
        respuesta.setNombre(guardado.getNombre());
        respuesta.setAño(guardado.getAño());
        return respuesta;
    }

    public List<CampeonatoResponseDto> obtenerCampeonatos(){
        List<Campeonato> campeonatos = campeonatoRepository.findAll();
        return campeonatos.stream().map(campeonato -> {
            CampeonatoResponseDto respuesta  = new CampeonatoResponseDto();
            respuesta.setId(campeonato.getId());
            respuesta.setNombre(campeonato.getNombre());
            respuesta.setAño(campeonato.getAño());
            List<PartidoDto> partidos = new ArrayList<>();
            for (Partido partido : campeonato.getPartidos()){
                PartidoDto dto = new PartidoDto();
                dto.setId(partido.getId());
                dto.setJornada(partido.getJornada());
                dto.setRival(partido.getRival());
                dto.setResultado(partido.getResultado());
                dto.setLocal(partido.getLocal());
                partidos.add(dto);
            }
            respuesta.setPartidos(partidos);
            return respuesta;
        }).toList();
    }

    public CampeonatoResponseDto obtenerCampeonato(Long id){
        Campeonato campeonato = campeonatoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Campeonato no encontrado"));
        CampeonatoResponseDto  respuesta = new CampeonatoResponseDto();
        respuesta.setId(campeonato.getId());
        respuesta.setNombre(campeonato.getNombre());
        respuesta.setAño(campeonato.getAño());
        List<PartidoDto> partidos = new ArrayList<>();
            for (Partido partido : campeonato.getPartidos()){
                PartidoDto dto = new PartidoDto();
                dto.setId(partido.getId());
                dto.setJornada(partido.getJornada());
                dto.setRival(partido.getRival());
                dto.setResultado(partido.getResultado());
                dto.setLocal(partido.getLocal());
                partidos.add(dto);
            }
        respuesta.setPartidos(partidos);
        return respuesta;
    }

    public void eliminarCampeonato(Long id){
        campeonatoRepository.deleteById(id);
    }
}

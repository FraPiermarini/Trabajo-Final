package com.example.Trabajo.Final.feature.Campeonato.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoPutDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoRequestDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;
import com.example.Trabajo.Final.feature.Campeonato.Models.Campeonato;
import com.example.Trabajo.Final.feature.Campeonato.Repositories.CampeonatoRepository;
import com.example.Trabajo.Final.feature.Campeonato.Services.Interface.CampeonatoService;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Partido.Dtos.PartidoDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampeonatoServiceImpl implements CampeonatoService{
    private final CampeonatoRepository campeonatoRepository;
    private final PartidoRepository partidoRepository;

    public CampeonatoResponseDto crearCampeonato(CampeonatoRequestDto dto){
        Campeonato nuevoCampeonato = new Campeonato();
        nuevoCampeonato.setNombre(dto.getNombre());
        nuevoCampeonato.setAño(dto.getAño());
        return convertirDto(campeonatoRepository.save(nuevoCampeonato));
    }

    public List<CampeonatoResponseDto> obtenerCampeonatos(){
        List<Campeonato> campeonatos = campeonatoRepository.findAll();
        return campeonatos.stream().map(campeonato -> {
            return convertirDto(campeonato);
        }).toList();
    }

    public CampeonatoResponseDto obtenerCampeonato(Long id){
        Campeonato campeonato = campeonatoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato con id " + id + " no encontrado"));
        return convertirDto(campeonato);
    }

    public void eliminarCampeonato(Long id){
        campeonatoRepository.deleteById(id);
    }

    @Override
    public CampeonatoResponseDto actualizarCampeonato(Long id, CampeonatoPutDto dto){
        Campeonato campeonato = campeonatoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Campeonato con id " + id + " no encontrado"));
        campeonato.setNombre(dto.getNombre());
        campeonato.setAño(dto.getAño());
        return convertirDto(campeonatoRepository.save(campeonato));
    }

    private CampeonatoResponseDto convertirDto(Campeonato c){
        CampeonatoResponseDto dto = new CampeonatoResponseDto();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setAño(c.getAño());
        List<PartidoDto> partidos = new ArrayList<>();
        for(Partido partido : c.getPartidos()){
            PartidoDto partidoDto = new PartidoDto();
            partidoDto.setId(partido.getId());
            partidoDto.setJornada(partido.getJornada());
            partidoDto.setRival(partido.getRival());
            partidoDto.setResultado(partido.getResultado());
            partidoDto.setLocal(partido.getLocal());
            partidos.add(partidoDto);
        }
        dto.setPartidos(partidos);
        return dto;
    }
}

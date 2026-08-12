package com.example.Trabajo.Final.feature.Campeonato.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Campeonato.Dtos.Request.CampeonatoRequestDto;
import com.example.Trabajo.Final.feature.Campeonato.Dtos.Response.CampeonatoResponseDto;
import com.example.Trabajo.Final.feature.Campeonato.Services.CampeonatoServiceImpl;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoPostController {
    @Autowired
    private CampeonatoServiceImpl campeonatoServiceImpl;

    @PostMapping()
    public CampeonatoResponseDto crearCampeonato(@RequestBody CampeonatoRequestDto dto){
        return campeonatoServiceImpl.crearCampeonato(dto);
    }
}

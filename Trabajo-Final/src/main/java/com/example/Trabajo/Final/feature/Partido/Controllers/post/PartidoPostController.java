package com.example.Trabajo.Final.feature.Partido.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Partido.Dtos.Request.PartidoRequestDto;
import com.example.Trabajo.Final.feature.Partido.Dtos.Response.PartidoResponseDto;
import com.example.Trabajo.Final.feature.Partido.Services.PartidoServiceImpl;

@RestController
@RequestMapping("/api/partidos")
public class PartidoPostController {
    @Autowired
    private PartidoServiceImpl partidoServiceImpl;

    @PostMapping()
    public PartidoResponseDto crearPartido(@RequestBody PartidoRequestDto dto){
        return partidoServiceImpl.crearPartido(dto);
    }
}

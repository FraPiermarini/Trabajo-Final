package com.example.Trabajo.Final.feature.Estadistica.Controllers.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Services.EstadisticaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaPostController {
    @Autowired
    private EstadisticaServiceImpl estadisticaServiceImpl;

    @PostMapping()
    public EstadisticaResponseDto crearEstadistica(@RequestBody EstadisticaRequestDto dto){
        return estadisticaServiceImpl.crearEstadistica(dto);
    }

}

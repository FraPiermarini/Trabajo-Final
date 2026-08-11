package com.example.Trabajo.Final.feature.Estadistica.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Estadistica.Services.EstadisticaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estadisticas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstadisticaDeleteController {
    @Autowired
    private EstadisticaServiceImpl estadisticaServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadistica(@PathVariable Long id){
        estadisticaServiceImpl.eliminarEstadistica(id);
        return ResponseEntity.noContent().build();
    }
}

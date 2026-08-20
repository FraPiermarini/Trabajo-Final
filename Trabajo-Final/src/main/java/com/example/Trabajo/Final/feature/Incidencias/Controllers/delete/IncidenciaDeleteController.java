package com.example.Trabajo.Final.feature.Incidencias.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Incidencias.Services.IncidenciaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IncidenciaDeleteController {
    @Autowired
    private IncidenciaServiceImpl incidenciaServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id){
        incidenciaServiceImpl.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }
}

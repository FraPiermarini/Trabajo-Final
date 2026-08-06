package com.example.Trabajo.Final.feature.Entrenador.Controladores.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Entrenador.Services.EntrenadorServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EntrenadorDeleteController {
    @Autowired
    private EntrenadorServiceImpl entrenadorServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id){
        entrenadorServiceImpl.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();

    }

}

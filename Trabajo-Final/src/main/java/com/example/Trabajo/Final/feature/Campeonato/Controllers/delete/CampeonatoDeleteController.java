package com.example.Trabajo.Final.feature.Campeonato.Controllers.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Trabajo.Final.feature.Campeonato.Services.CampeonatoServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
@Tag(name =  "Campeonatos", description = "Operaciones relacionadas con campeonatos")
@RestController
@RequestMapping("/api/campeonatos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CampeonatoDeleteController {
    @Autowired
    private CampeonatoServiceImpl campeonatoServiceImpl;

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCampeonato(@PathVariable Long id) {
            campeonatoServiceImpl.eliminarCampeonato(id);
            return ResponseEntity.noContent().build();
        }
}

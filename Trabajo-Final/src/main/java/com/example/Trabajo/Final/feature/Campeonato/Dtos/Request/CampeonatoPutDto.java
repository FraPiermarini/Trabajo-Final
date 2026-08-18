package com.example.Trabajo.Final.feature.Campeonato.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampeonatoPutDto {
    private String nombre;
    private String año;
}

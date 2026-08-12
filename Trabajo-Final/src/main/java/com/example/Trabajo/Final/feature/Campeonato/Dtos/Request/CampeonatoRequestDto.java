package com.example.Trabajo.Final.feature.Campeonato.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampeonatoRequestDto {
    
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El año es requerido")
    private String año;


}

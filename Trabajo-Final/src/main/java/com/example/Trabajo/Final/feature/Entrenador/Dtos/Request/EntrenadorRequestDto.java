package com.example.Trabajo.Final.feature.Entrenador.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorRequestDto {
    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "El nombre solo puede contener letras") 
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "El apellido solo puede contener letras") 
    private String apellido;

    @NotNull(message = "La edad es requerida")
    private Integer edad;
}

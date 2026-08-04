package com.example.Trabajo.Final.feature.Categoria.Dtos.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDto {
    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "El nombre solo puede contener letras") 
    private String nombre;

    @NotBlank(message = "El año es requerido")
    private String año;

    @NotBlank(message = "La descripcion es requerida")
    @Size(min = 5, max = 150, message = "La descripción debe tener entre 10 y 150 caracteres")
    private String descripcion;

    

}

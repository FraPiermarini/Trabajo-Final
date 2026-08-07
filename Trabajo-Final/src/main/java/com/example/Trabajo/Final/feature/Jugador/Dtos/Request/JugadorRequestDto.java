package com.example.Trabajo.Final.feature.Jugador.Dtos.Request;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorRequestDto {
    @NotBlank(message = "El nombre es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "El nombre solo puede contener letras") 
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "El apellido solo puede contener letras")
    private String apellido;

    @NotBlank(message = "El dni es requerido")
    @Size(min = 7, max = 8, message = "El dni debe contener de 7 a 8 caracteres")
    private String dni;

    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "La posicion es requerida")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$" , message = "La posicion solo puede contener letras")
    private String posicion;

    @NotNull(message = "El numero de camiseta es requerido")
    @Max(99)
    private Integer numeroCamiseta;

    private Long categoriaId;


}

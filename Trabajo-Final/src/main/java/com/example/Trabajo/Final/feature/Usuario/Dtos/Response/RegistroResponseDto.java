package com.example.Trabajo.Final.feature.Usuario.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroResponseDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
}

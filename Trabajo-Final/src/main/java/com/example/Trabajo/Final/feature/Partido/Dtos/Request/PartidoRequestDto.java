package com.example.Trabajo.Final.feature.Partido.Dtos.Request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoRequestDto {
    @NotNull(message = "La fecha es requerida")
    private LocalDate fecha;

    @NotBlank(message = "La jornada es requerida")
    private String jornada;

    @NotBlank(message = "El rival es requerido")
    private String rival;

    @NotNull(message = "El id del campeonato es requerido")
    private Long campeonatoId;

    @NotBlank(message = "El resultado es requerido")
    private String resultado;

    @NotNull
    private Boolean local;

    @NotNull(message = "El id de la categoria es requerido")
    private Long categoriaId;

    @NotNull(message = "El id del entrenador es requerido")
    private Long entrenadorId;


}

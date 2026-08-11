package com.example.Trabajo.Final.feature.Partido.Dtos.Request;

import java.time.LocalDate;
import java.util.List;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoRequestDto {
    @NotBlank(message = "La fecha es requerida")
    private LocalDate fecha;

    @NotBlank(message = "La jornada es requerida")
    private String jornada;

    @NotBlank(message = "El rival es requerido")
    private String rival;

   /*  @NotBlank(message = "El id del campeonato es requerido")
    private Long campeonatoId;*/

    @NotBlank(message = "El resultado es requerido")
    private String resultado;

    @NotNull
    private Boolean local;

    @NotBlank(message = "El id de la categoria es requerido")
    private Long categoriaId;

    @Valid 
    private List<EstadisticaRequestDto> estadisticas;


}

package com.example.Trabajo.Final.feature.Estadistica.Services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaPutRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteCategoriaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteEntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteEstadisticaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Models.Estadistica;
import com.example.Trabajo.Final.feature.Estadistica.Repositories.EstadisticaRepository;
import com.example.Trabajo.Final.feature.Exceptions.RecursoNoEncontradoException;
import com.example.Trabajo.Final.feature.Incidencias.Dtos.Response.IncidenciaResponseDto;
import com.example.Trabajo.Final.feature.Incidencias.Models.Incidencia;
import com.example.Trabajo.Final.feature.Incidencias.Repositories.IncidenciaRepository;
import com.example.Trabajo.Final.feature.Jugador.Models.Jugador;
import com.example.Trabajo.Final.feature.Jugador.Repositories.JugadorRepository;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;
import com.example.Trabajo.Final.feature.Partido.Repositories.PartidoRepository;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteCategoriaResponseDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.ReporteEntrenadorResponseDto;
import com.example.Trabajo.Final.feature.Partido.Models.Partido;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl {
    private final EstadisticaRepository estadisticaRepository;
    private final IncidenciaRepository incidenciaRepository; 
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    public EstadisticaResponseDto crearEstadistica(EstadisticaRequestDto dto){
        Estadistica nuevaEstadistica = new Estadistica();
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
            .orElseThrow(() -> new RecursoNoEncontradoException("Jugador con id " + dto.getJugadorId() + " no encontrado"));
        nuevaEstadistica.setJugador(jugador);
        Partido partido = partidoRepository.findById(dto.getPartidoId())
             .orElseThrow(() -> new RecursoNoEncontradoException("Partido con id " + dto.getPartidoId() + " no encontrado"));
        Optional<Estadistica> existente = estadisticaRepository.findByJugadorIdAndPartidoId(dto.getJugadorId(), dto.getPartidoId());
        if(existente.isPresent()){
            throw new IllegalArgumentException("El jugador " + dto.getJugadorId() + "ya tiene una estadistica registrada para el partido " + dto.getPartidoId());
        }
        nuevaEstadistica.setPartido(partido);
        nuevaEstadistica.setMinutos(dto.getMinutos());
        nuevaEstadistica.setGoles(dto.getGoles());
        nuevaEstadistica.setAsistencias(dto.getAsistencias());
        nuevaEstadistica.setRojas(dto.getRojas());
        nuevaEstadistica.setAmarillas((dto.getAmarillas()));
        nuevaEstadistica.setTitular(dto.getTitular());
        Estadistica guardado = estadisticaRepository.save(nuevaEstadistica);

        
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
        respuesta.setId(guardado.getId());
        if (guardado.getJugador() != null){
        respuesta.setJugadorId(guardado.getJugador().getId());   
        }
        if(guardado.getPartido() != null){
            respuesta.setPartidoId(guardado.getPartido().getId());
        }
        respuesta.setMinutos(guardado.getMinutos());
        respuesta.setGoles(guardado.getGoles());
        respuesta.setAsistencias(guardado.getAsistencias());
        respuesta.setRojas(guardado.getRojas());
        respuesta.setAmarillas(guardado.getAmarillas());
        respuesta.setTitular(guardado.getTitular());
        return respuesta;
    }

    public List<EstadisticaResponseDto> obtenerEstadisticas(){
        List<Estadistica> estadisticas = estadisticaRepository.findAll();
        return estadisticas.stream().map(estadistica -> {
            EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
            respuesta.setId(estadistica.getId());
            if(estadistica.getJugador() != null){
                respuesta.setJugadorId(estadistica.getJugador().getId());
            }
            if(estadistica.getPartido() != null){
                respuesta.setPartidoId(estadistica.getPartido().getId());
            }
            respuesta.setMinutos(estadistica.getMinutos());
            respuesta.setGoles(estadistica.getGoles());
            respuesta.setAsistencias(estadistica.getAsistencias());
            respuesta.setRojas(estadistica.getRojas());
            respuesta.setAmarillas(estadistica.getAmarillas());
            respuesta.setTitular(estadistica.getTitular());
            return respuesta;
        }).toList();
    }

    public EstadisticaResponseDto obtenerEstadistica(Long id){
        Estadistica estadistica = estadisticaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Estadistica con id " + id + " no encontrada"));
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
        respuesta.setId(estadistica.getId());
        if (estadistica.getJugador() != null){
        respuesta.setJugadorId(estadistica.getJugador().getId());   
        }
        if (estadistica.getPartido() != null){
        respuesta.setPartidoId(estadistica.getPartido().getId());   
        }
        respuesta.setMinutos(estadistica.getMinutos());
        respuesta.setGoles(estadistica.getGoles());
        respuesta.setAsistencias(estadistica.getAsistencias());
        respuesta.setRojas(estadistica.getRojas());
        respuesta.setAmarillas(estadistica.getAmarillas());
        respuesta.setTitular(estadistica.getTitular());
        return respuesta;
    }

    public EstadisticaResponseDto actualizarEstadistica(Long id, EstadisticaPutRequestDto dto){
        Estadistica estadistica = estadisticaRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Estadistica con id " + id + " no encontrada"));
        if(dto.getMinutos() != null){
            estadistica.setMinutos(dto.getMinutos());
        }
        if(dto.getGoles() != null){
            estadistica.setGoles(dto.getGoles());
        }
        if(dto.getAsistencias() != null){
            estadistica.setAsistencias(dto.getAsistencias());
        }
        if(dto.getRojas() != null){
            estadistica.setRojas(dto.getRojas());
        }
        if(dto.getAmarillas() != null){
            estadistica.setAmarillas(dto.getAmarillas());
        }
        if(dto.getTitular() != null){
            estadistica.setTitular(dto.getTitular());
        }
        Estadistica actualizada = estadisticaRepository.save(estadistica);
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
        respuesta.setId(actualizada.getId());
        if (actualizada.getJugador() != null){
        respuesta.setJugadorId(actualizada.getJugador().getId());   
        }
        if (actualizada.getPartido() != null){
        respuesta.setPartidoId(actualizada.getPartido().getId());   
        }
        respuesta.setMinutos(actualizada.getMinutos());
        respuesta.setGoles(actualizada.getGoles());
        respuesta.setAsistencias(actualizada.getAsistencias());
        respuesta.setRojas(actualizada.getRojas());
        respuesta.setAmarillas(actualizada.getAmarillas());
        respuesta.setTitular(actualizada.getTitular());
        return respuesta;
    }

    public void eliminarEstadistica(Long id){
        estadisticaRepository.deleteById(id);
    }

    public ReporteEstadisticaResponseDto obtenerEstadisticaAcumulada(Long jugadorId){
        List<Estadistica> estadisticas = estadisticaRepository.findByJugadorId(jugadorId);
        if(estadisticas.isEmpty()){
            throw new RecursoNoEncontradoException("No hay estadisticas para el jugador con id " + jugadorId );
        }
        int partidos = estadisticas.size();
        int titulares =(int) estadisticas.stream().filter(e -> Boolean.TRUE.equals(e.getTitular())).count();
        int minutos = (int) estadisticas.stream().mapToInt(e -> e.getMinutos() != null ? e.getMinutos() : 0).sum();
        int goles = (int) estadisticas.stream().mapToInt(e -> e.getGoles() != null ? e.getGoles() : 0).sum();
        int asistencias = (int) estadisticas.stream().mapToInt(e -> e.getAsistencias() != null ? e.getAsistencias() : 0).sum();
        int rojas = (int) estadisticas.stream().mapToInt(e -> e.getRojas() != null ? e.getRojas() : 0).sum();
        int amarillas = (int) estadisticas.stream().mapToInt(e -> e.getAmarillas() != null ? e.getAmarillas() : 0).sum();
        List<Incidencia> incidencias = incidenciaRepository.findByJugadorId(jugadorId);
        List<IncidenciaResponseDto> incidenciasDto = new ArrayList<>();
        int diasIncidencias = 0;
        for(Incidencia incidencia : incidencias){
            IncidenciaResponseDto dto = new IncidenciaResponseDto();
            dto.setId(incidencia.getId());
            dto.setJugadorId(incidencia.getJugador().getId());
            dto.setFecha(incidencia.getFecha());
            dto.setMotivo(incidencia.getMotivo());
            dto.setCantidadDias(incidencia.getCantidadDias());
            if(incidencia.getPartido() != null){
                dto.setPartidoId(incidencia.getPartido().getId());
            }
            incidenciasDto.add(dto);
            diasIncidencias += incidencia.getCantidadDias() != null ? incidencia.getCantidadDias() : 0;

        }
        return new ReporteEstadisticaResponseDto(
            jugadorId, partidos, titulares, minutos, goles, asistencias, rojas ,amarillas, incidenciasDto, diasIncidencias
        );
    }

    public List<ReporteEstadisticaResponseDto> obtenerEstadisticasPorMes(Integer año, Integer mes){
        YearMonth yearMonth = YearMonth.of(año, mes);
        LocalDate inicio = yearMonth.atDay(1);
        LocalDate fin = yearMonth.atEndOfMonth();
        List<Partido> partidos = partidoRepository.buscarPartidosEntreFechas(inicio, fin);
        Map<Long, ReporteEstadisticaResponseDto> acumulados = new HashMap<>();
        for( Partido partido : partidos){
            List<Estadistica> estadisticas = estadisticaRepository.findByPartidoId(partido.getId());
        for(Estadistica estadistica : estadisticas){
            Long jugadorId = estadistica.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                reporte.setIncidencias(new ArrayList<>());
                acumulados.put(jugadorId, reporte);
            }
            reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0));
        }
        }
        List<Incidencia> incidencias = incidenciaRepository.findByFechaBetween(inicio, fin);
        for(Incidencia incidencia : incidencias){
            Long jugadorId = incidencia.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                reporte.setIncidencias(new ArrayList<>());
                acumulados.put(jugadorId, reporte);
            }
            if(reporte.getIncidencias() == null){
                reporte.setIncidencias(new ArrayList<>());
            }
            IncidenciaResponseDto  dto = new IncidenciaResponseDto();
            dto.setId(incidencia.getId());
            dto.setJugadorId(incidencia.getJugador().getId());
            dto.setFecha(incidencia.getFecha());
            dto.setMotivo(incidencia.getMotivo());
            dto.setCantidadDias(incidencia.getCantidadDias());
            if(incidencia.getPartido() != null){
                dto.setPartidoId(incidencia.getPartido().getId());
            }
            reporte.getIncidencias().add(dto);
        }
        return new ArrayList<>(acumulados.values());
    }

    public List<ReporteEstadisticaResponseDto> obtenerEstadisticasPorCampeonato(Long campeonatoId){
        List<Partido> partidos = partidoRepository.findByCampeonatoId(campeonatoId);
        Map<Long, ReporteEstadisticaResponseDto> acumulados = new HashMap<>();
        for( Partido partido : partidos){
            List<Estadistica> estadisticas = estadisticaRepository.findByPartidoId(partido.getId());
        for(Estadistica estadistica : estadisticas){
            Long jugadorId = estadistica.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                reporte.setIncidencias(new ArrayList<>());
                acumulados.put(jugadorId, reporte);
            }
            reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0));
        }
        } 
        for(Partido partido : partidos){
            List<Incidencia> incidencias = incidenciaRepository.findByPartidoId(partido.getId());
            for(Incidencia incidencia : incidencias){
            Long jugadorId = incidencia.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                reporte.setIncidencias(new ArrayList<>());
                acumulados.put(jugadorId, reporte);
            }
            if(reporte.getIncidencias() == null){
                reporte.setIncidencias(new ArrayList<>());
            }
            IncidenciaResponseDto  dto = new IncidenciaResponseDto();
            dto.setId(incidencia.getId());
            dto.setJugadorId(incidencia.getJugador().getId());
            dto.setFecha(incidencia.getFecha());
            dto.setMotivo(incidencia.getMotivo());
            dto.setCantidadDias(incidencia.getCantidadDias());
            if(incidencia.getPartido() != null){
                dto.setPartidoId(incidencia.getPartido().getId());
            }
            reporte.getIncidencias().add(dto);
        }
        }
        return new ArrayList<>(acumulados.values());
    }

    public List<ReporteEstadisticaResponseDto> obtenerEstadisticasPorPartido(Long partidoId){
        List<Estadistica> estadisticas = estadisticaRepository.findByPartidoId(partidoId);
        Map<Long, ReporteEstadisticaResponseDto> acumulados = new HashMap<>();
        for(Estadistica estadistica : estadisticas){
            Long jugadorId = estadistica.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                acumulados.put(jugadorId, reporte);
            }
            reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0));
        }
        List<Incidencia> incidencias = incidenciaRepository.findByPartidoId(partidoId);
        for(Incidencia incidencia : incidencias){
            Long jugadorId = incidencia.getJugador().getId();
            ReporteEstadisticaResponseDto reporte = acumulados.get(jugadorId);
            if(reporte == null){
                reporte = new ReporteEstadisticaResponseDto();
                reporte.setJugadorId(jugadorId);
                reporte.setPartidos(0);
                reporte.setTitulares(0);
                reporte.setMinutos(0);
                reporte.setGoles(0);
                reporte.setAsistencias(0);
                reporte.setRojas(0);
                reporte.setAmarillas(0);
                reporte.setIncidencias(new ArrayList<>());
                acumulados.put(jugadorId, reporte);
            }
            if(reporte.getIncidencias() == null){
                reporte.setIncidencias(new ArrayList<>());
            }
            IncidenciaResponseDto  dto = new IncidenciaResponseDto();
            dto.setId(incidencia.getId());
            dto.setJugadorId(incidencia.getJugador().getId());
            dto.setFecha(incidencia.getFecha());
            dto.setMotivo(incidencia.getMotivo());
            dto.setCantidadDias(incidencia.getCantidadDias());
            if(incidencia.getPartido() != null){
                dto.setPartidoId(incidencia.getPartido().getId());
            }
            reporte.getIncidencias().add(dto);
        }
        return new ArrayList<>(acumulados.values());
        } 

        public ReporteEstadisticaResponseDto obtenerEstadisticaPartidoJugador(Long partidoId, Long jugadorId){
            Estadistica estadistica = estadisticaRepository.findByJugadorIdAndPartidoId(jugadorId, partidoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontraron estadisticas del jugador " + jugadorId  + " en el partido" + partidoId));
            ReporteEstadisticaResponseDto reporte = new ReporteEstadisticaResponseDto();
            reporte = new ReporteEstadisticaResponseDto();
            reporte.setJugadorId(jugadorId);
            reporte.setPartidos(0);
            reporte.setTitulares(0);
            reporte.setMinutos(0);
            reporte.setGoles(0);
            reporte.setAsistencias(0);
            reporte.setRojas(0);
            reporte.setAmarillas(0);
            reporte.setIncidencias(new ArrayList<>());
            reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0));
            List<Incidencia> incidencias = incidenciaRepository.findByJugadorIdAndPartidoId(jugadorId, partidoId);
            List<IncidenciaResponseDto> incidenciasDto = new ArrayList<>();
            int diasIncidencias  = 0;
            for(Incidencia incidencia : incidencias){
                IncidenciaResponseDto dto = new IncidenciaResponseDto();
                dto.setId(incidencia.getId());
                dto.setJugadorId(incidencia.getJugador().getId());
                dto.setPartidoId(partidoId);
                dto.setFecha(incidencia.getFecha());
                dto.setMotivo(incidencia.getMotivo());
                dto.setCantidadDias(incidencia.getCantidadDias());
                incidenciasDto.add(dto);
                diasIncidencias += incidencia.getCantidadDias() != null ? incidencia.getCantidadDias() : 0;
            }
            reporte.setIncidencias(incidenciasDto);
            reporte.setDiasIncidencias(diasIncidencias);
            return reporte;
        }

    public ReporteEstadisticaResponseDto obtenerEstadisticasMesJugador(Integer año, Integer mes, Long jugadorId){
        YearMonth yearMonth = YearMonth.of(año, mes);
        LocalDate inicio = yearMonth.atDay(1);
        LocalDate fin = yearMonth.atEndOfMonth();
        List<Partido> partidos = partidoRepository.buscarPartidosEntreFechas(inicio, fin);
        ReporteEstadisticaResponseDto reporte = new ReporteEstadisticaResponseDto();
            reporte = new ReporteEstadisticaResponseDto();
            reporte.setJugadorId(jugadorId);
            reporte.setPartidos(0);
            reporte.setTitulares(0);
            reporte.setMinutos(0);
            reporte.setGoles(0);
            reporte.setAsistencias(0);
            reporte.setRojas(0);
            reporte.setAmarillas(0);
            reporte.setIncidencias(new ArrayList<>());

            boolean encontroEstadistica = false;
            for(Partido partido : partidos){
                Optional<Estadistica> resultado = estadisticaRepository.findByJugadorIdAndPartidoId(jugadorId, partido.getId());
                if(resultado.isPresent()){
                    encontroEstadistica = true;
                    Estadistica estadistica = resultado.get();
                   reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0)); 
            }
            }
            List<Incidencia> incidencias = incidenciaRepository.findByJugadorId(jugadorId);
            int diasIncidencias = 0;
            for (Incidencia incidencia : incidencias) {
                if (!incidencia.getFecha().isBefore(inicio) && !incidencia.getFecha().isAfter(fin)) {
                    IncidenciaResponseDto dto = new IncidenciaResponseDto();
                    dto.setId(incidencia.getId());
                    dto.setJugadorId(jugadorId);
                    dto.setFecha(incidencia.getFecha());
                    dto.setMotivo(incidencia.getMotivo());
                    dto.setCantidadDias(incidencia.getCantidadDias());
                    if (incidencia.getPartido() != null) {
                        dto.setPartidoId(incidencia.getPartido().getId());
                    }
                    reporte.getIncidencias().add(dto);
                    diasIncidencias += incidencia.getCantidadDias() != null ? incidencia.getCantidadDias() : 0;
            }
        }

            reporte.setDiasIncidencias(diasIncidencias);
            if(!encontroEstadistica){
                throw new RecursoNoEncontradoException("No se encontraron estadisticas del jugador " + jugadorId + " en el mes " + mes);
            }
            return reporte;
        } 

        public ReporteEstadisticaResponseDto obtenerEstadisticasCampeonatoJugador(Long campeonatoId, Long jugadorId){
            List<Partido> partidos = partidoRepository.findByCampeonatoId(campeonatoId);
            ReporteEstadisticaResponseDto reporte = new ReporteEstadisticaResponseDto();
            reporte = new ReporteEstadisticaResponseDto();
            reporte.setJugadorId(jugadorId);
            reporte.setPartidos(0);
            reporte.setTitulares(0);
            reporte.setMinutos(0);
            reporte.setGoles(0);
            reporte.setAsistencias(0);
            reporte.setRojas(0);
            reporte.setAmarillas(0);
            reporte.setIncidencias(new ArrayList<>());
            boolean encontroEstadistica = false;
            for(Partido partido : partidos){
                Optional<Estadistica> resultado = estadisticaRepository.findByJugadorIdAndPartidoId(jugadorId, partido.getId());
                if(resultado.isPresent()){
                    encontroEstadistica = true;
                    Estadistica estadistica = resultado.get();
                   reporte.setPartidos(reporte.getPartidos() + 1);
            if(Boolean.TRUE.equals(estadistica.getTitular())){
                reporte.setTitulares(reporte.getTitulares() + 1);
            }
            reporte.setMinutos(reporte.getMinutos() + (estadistica.getMinutos() != null ? estadistica.getMinutos() : 0));
            reporte.setGoles(reporte.getGoles() + (estadistica.getGoles() != null ? estadistica.getGoles() : 0));
            reporte.setAsistencias(reporte.getAsistencias() + (estadistica.getAsistencias() != null ? estadistica.getAsistencias() : 0));
            reporte.setRojas(reporte.getRojas() + (estadistica.getRojas() != null ? estadistica.getRojas() : 0));
            reporte.setAmarillas(reporte.getAmarillas() + (estadistica.getAmarillas() != null ? estadistica.getAmarillas() : 0)); 
            }
            }
            int diasIncidencias = 0;
            for (Partido partido : partidos) {
                List<Incidencia> incidencias = incidenciaRepository.findByJugadorIdAndPartidoId(jugadorId, partido.getId());
                for (Incidencia incidencia : incidencias) {
                    IncidenciaResponseDto dto = new IncidenciaResponseDto();
                    dto.setId(incidencia.getId());
                    dto.setJugadorId(jugadorId);
                    dto.setFecha(incidencia.getFecha());
                    dto.setMotivo(incidencia.getMotivo());
                    dto.setCantidadDias(incidencia.getCantidadDias());
                    if (incidencia.getPartido() != null) {
                        dto.setPartidoId(incidencia.getPartido().getId());
                    }
                    reporte.getIncidencias().add(dto);

            diasIncidencias += incidencia.getCantidadDias() != null ? incidencia.getCantidadDias() : 0;
            }
        }
            reporte.setDiasIncidencias(diasIncidencias);
            if(!encontroEstadistica){
                throw new RecursoNoEncontradoException("No se encontraron estadsiticas del jugador " + jugadorId + " en el campeonato " + campeonatoId);
            }
            return reporte;

        }
public List<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresAcumulado() {

    List<Partido> partidos = partidoRepository.findAll();

    Map<Long, ReporteEntrenadorResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getEntrenador() == null) {
            continue;
        }

        Long entrenadorId = partido.getEntrenador().getId();

        ReporteEntrenadorResponseDto reporte = reportes.get(entrenadorId);

        if (reporte == null) {

            reporte = new ReporteEntrenadorResponseDto();

            reporte.setEntrenadorId(entrenadorId);

            reporte.setEntrenadorNombre(
                    partido.getEntrenador().getNombre()
                            + " "
                            + partido.getEntrenador().getApellido()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(entrenadorId, reporte);
        }

        agregarPartidoEntrenador(reporte, partido);
    }

    for (ReporteEntrenadorResponseDto reporte : reportes.values()) {
        calcularPorcentajesEntrenador(reporte);
    }

    return reportes.values().stream().toList();
}

public ReporteEntrenadorResponseDto obtenerReporteEntrenadorAcumulado(
        Long entrenadorId) {

    List<Partido> partidos =
            partidoRepository.findByEntrenadorId(entrenadorId);

    if (partidos.isEmpty()) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para el entrenador con id "
                        + entrenadorId
        );
    }

    ReporteEntrenadorResponseDto reporte =
            crearReporteEntrenador(partidos.get(0));

    for (Partido partido : partidos) {
        agregarPartidoEntrenador(reporte, partido);
    }

    calcularPorcentajesEntrenador(reporte);

    return reporte;
}


public List<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresPorMes(
        Integer año,
        Integer mes) {

    YearMonth yearMonth = YearMonth.of(año, mes);

    LocalDate inicio = yearMonth.atDay(1);
    LocalDate fin = yearMonth.atEndOfMonth();

    List<Partido> partidos =
            partidoRepository.buscarPartidosEntreFechas(inicio, fin);

    Map<Long, ReporteEntrenadorResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getEntrenador() == null) {
            continue;
        }

        Long entrenadorId = partido.getEntrenador().getId();

        ReporteEntrenadorResponseDto reporte = reportes.get(entrenadorId);

        if (reporte == null) {

            reporte = new ReporteEntrenadorResponseDto();

            reporte.setEntrenadorId(entrenadorId);

            reporte.setEntrenadorNombre(
                    partido.getEntrenador().getNombre()
                            + " "
                            + partido.getEntrenador().getApellido()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(entrenadorId, reporte);
        }

        agregarPartidoEntrenador(reporte, partido);
    }

    for (ReporteEntrenadorResponseDto reporte : reportes.values()) {
        calcularPorcentajesEntrenador(reporte);
    }

    return reportes.values().stream().toList();
}
public ReporteEntrenadorResponseDto obtenerReporteEntrenadorPorMes(
        Long entrenadorId,
        Integer año,
        Integer mes) {

    YearMonth yearMonth = YearMonth.of(año, mes);

    LocalDate inicio = yearMonth.atDay(1);
    LocalDate fin = yearMonth.atEndOfMonth();

    List<Partido> partidos =
            partidoRepository.buscarPartidosEntreFechas(inicio, fin);

    ReporteEntrenadorResponseDto reporte = null;

    for (Partido partido : partidos) {

        if (partido.getEntrenador() == null) {
            continue;
        }

        if (!partido.getEntrenador().getId().equals(entrenadorId)) {
            continue;
        }

        if (reporte == null) {
            reporte = crearReporteEntrenador(partido);
        }

        agregarPartidoEntrenador(reporte, partido);
    }

    if (reporte == null) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para el entrenador con id "
                        + entrenadorId
                        + " en "
                        + mes
                        + "/"
                        + año
        );
    }

    calcularPorcentajesEntrenador(reporte);

    return reporte;
}


public List<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresPorCampeonato(
        Long campeonatoId) {

    List<Partido> partidos =
            partidoRepository.findByCampeonatoId(campeonatoId);

    Map<Long, ReporteEntrenadorResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getEntrenador() == null) {
            continue;
        }

        Long entrenadorId = partido.getEntrenador().getId();

        ReporteEntrenadorResponseDto reporte = reportes.get(entrenadorId);

        if (reporte == null) {

            reporte = new ReporteEntrenadorResponseDto();

            reporte.setEntrenadorId(entrenadorId);

            reporte.setEntrenadorNombre(
                    partido.getEntrenador().getNombre()
                            + " "
                            + partido.getEntrenador().getApellido()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(entrenadorId, reporte);
        }

        agregarPartidoEntrenador(reporte, partido);
    }

    for (ReporteEntrenadorResponseDto reporte : reportes.values()) {
        calcularPorcentajesEntrenador(reporte);
    }

    return reportes.values().stream().toList();
}
public ReporteEntrenadorResponseDto obtenerReporteEntrenadorPorCampeonato(
        Long entrenadorId,
        Long campeonatoId) {

    List<Partido> partidos =
            partidoRepository.findByCampeonatoId(campeonatoId);

    ReporteEntrenadorResponseDto reporte = null;

    for (Partido partido : partidos) {

        if (partido.getEntrenador() == null) {
            continue;
        }

        if (!partido.getEntrenador().getId().equals(entrenadorId)) {
            continue;
        }

        if (reporte == null) {
            reporte = crearReporteEntrenador(partido);
        }

        agregarPartidoEntrenador(reporte, partido);
    }

    if (reporte == null) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para el entrenador con id "
                        + entrenadorId
                        + " en el campeonato "
                        + campeonatoId
        );
    }

    calcularPorcentajesEntrenador(reporte);

    return reporte;
}


public List<ReporteEntrenadorResponseDto> obtenerReporteEntrenadoresPorPartido(
        Long partidoId) {

    Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Partido con id " + partidoId + " no encontrado"
                    )
            );

    if (partido.getEntrenador() == null) {
        return List.of();
    }

    ReporteEntrenadorResponseDto reporte =
            new ReporteEntrenadorResponseDto();

    reporte.setEntrenadorId(partido.getEntrenador().getId());

    reporte.setEntrenadorNombre(
            partido.getEntrenador().getNombre()
                    + " "
                    + partido.getEntrenador().getApellido()
    );

    reporte.setPartidos(0);
    reporte.setVictorias(0);
    reporte.setEmpates(0);
    reporte.setDerrotas(0);
    reporte.setGoles(0);
    reporte.setGolesEnContra(0);
    reporte.setDiferenciaGol(0);
    reporte.setPuntos(0);

    agregarPartidoEntrenador(reporte, partido);

    calcularPorcentajesEntrenador(reporte);

    return List.of(reporte);
}
public ReporteEntrenadorResponseDto obtenerReporteEntrenadorPorPartido(
        Long entrenadorId,
        Long partidoId) {

    Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Partido con id "
                                    + partidoId
                                    + " no encontrado"
                    )
            );

    if (partido.getEntrenador() == null ||
            !partido.getEntrenador().getId().equals(entrenadorId)) {

        throw new RecursoNoEncontradoException(
                "El partido no pertenece al entrenador con id "
                        + entrenadorId
        );
    }

    ReporteEntrenadorResponseDto reporte =
            crearReporteEntrenador(partido);

    agregarPartidoEntrenador(reporte, partido);

    calcularPorcentajesEntrenador(reporte);

    return reporte;
}
private ReporteEntrenadorResponseDto crearReporteEntrenador(
        Partido partido) {

    ReporteEntrenadorResponseDto reporte =
            new ReporteEntrenadorResponseDto();

    reporte.setEntrenadorId(
            partido.getEntrenador().getId()
    );

    reporte.setEntrenadorNombre(
            partido.getEntrenador().getNombre()
                    + " "
                    + partido.getEntrenador().getApellido()
    );

    reporte.setPartidos(0);
    reporte.setVictorias(0);
    reporte.setEmpates(0);
    reporte.setDerrotas(0);
    reporte.setGoles(0);
    reporte.setGolesEnContra(0);
    reporte.setDiferenciaGol(0);
    reporte.setPuntos(0);

    return reporte;
}

public List<ReporteCategoriaResponseDto> obtenerReporteCategoriasAcumulado() {

    List<Partido> partidos = partidoRepository.findAll();

    Map<Long, ReporteCategoriaResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getCategoria() == null) {
            continue;
        }

        Long categoriaId = partido.getCategoria().getId();

        ReporteCategoriaResponseDto reporte = reportes.get(categoriaId);

        if (reporte == null) {

            reporte = new ReporteCategoriaResponseDto();

            reporte.setCategoriaId(categoriaId);

            reporte.setCategoriaNombre(
                    partido.getCategoria().getNombre()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(categoriaId, reporte);
        }

        agregarPartidoCategoria(reporte, partido);
    }

    for (ReporteCategoriaResponseDto reporte : reportes.values()) {
        calcularPorcentajesCategoria(reporte);
    }

    return reportes.values().stream().toList();
}
public ReporteCategoriaResponseDto obtenerReporteCategoriaAcumulado(
        Long categoriaId) {

    List<Partido> partidos =
            partidoRepository.findByCategoriaId(categoriaId);

    if (partidos.isEmpty()) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para la categoria con id "
                        + categoriaId
        );
    }

    ReporteCategoriaResponseDto reporte =
            crearReporteCategoria(partidos.get(0));

    for (Partido partido : partidos) {
        agregarPartidoCategoria(reporte, partido);
    }

    calcularPorcentajesCategoria(reporte);

    return reporte;
}


public List<ReporteCategoriaResponseDto> obtenerReporteCategoriasPorMes(
        Integer año,
        Integer mes) {

    YearMonth yearMonth = YearMonth.of(año, mes);

    LocalDate inicio = yearMonth.atDay(1);
    LocalDate fin = yearMonth.atEndOfMonth();

    List<Partido> partidos =
            partidoRepository.buscarPartidosEntreFechas(inicio, fin);

    Map<Long, ReporteCategoriaResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getCategoria() == null) {
            continue;
        }

        Long categoriaId = partido.getCategoria().getId();

        ReporteCategoriaResponseDto reporte = reportes.get(categoriaId);

        if (reporte == null) {

            reporte = new ReporteCategoriaResponseDto();

            reporte.setCategoriaId(categoriaId);

            reporte.setCategoriaNombre(
                    partido.getCategoria().getNombre()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(categoriaId, reporte);
        }

        agregarPartidoCategoria(reporte, partido);
    }

    for (ReporteCategoriaResponseDto reporte : reportes.values()) {
        calcularPorcentajesCategoria(reporte);
    }

    return reportes.values().stream().toList();
}
public ReporteCategoriaResponseDto obtenerReporteCategoriaPorMes(
        Long categoriaId,
        Integer año,
        Integer mes) {

    YearMonth yearMonth = YearMonth.of(año, mes);

    LocalDate inicio = yearMonth.atDay(1);
    LocalDate fin = yearMonth.atEndOfMonth();

    List<Partido> partidos =
            partidoRepository.buscarPartidosEntreFechas(inicio, fin);

    ReporteCategoriaResponseDto reporte = null;

    for (Partido partido : partidos) {

        if (partido.getCategoria() == null) {
            continue;
        }

        if (!partido.getCategoria().getId().equals(categoriaId)) {
            continue;
        }

        if (reporte == null) {
            reporte = crearReporteCategoria(partido);
        }

        agregarPartidoCategoria(reporte, partido);
    }

    if (reporte == null) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para la categoria con id "
                        + categoriaId
                        + " en "
                        + mes
                        + "/"
                        + año
        );
    }

    calcularPorcentajesCategoria(reporte);

    return reporte;
}


public List<ReporteCategoriaResponseDto> obtenerReporteCategoriasPorCampeonato(
        Long campeonatoId) {

    List<Partido> partidos =
            partidoRepository.findByCampeonatoId(campeonatoId);

    Map<Long, ReporteCategoriaResponseDto> reportes = new HashMap<>();

    for (Partido partido : partidos) {

        if (partido.getCategoria() == null) {
            continue;
        }

        Long categoriaId = partido.getCategoria().getId();

        ReporteCategoriaResponseDto reporte = reportes.get(categoriaId);

        if (reporte == null) {

            reporte = new ReporteCategoriaResponseDto();

            reporte.setCategoriaId(categoriaId);

            reporte.setCategoriaNombre(
                    partido.getCategoria().getNombre()
            );

            reporte.setPartidos(0);
            reporte.setVictorias(0);
            reporte.setEmpates(0);
            reporte.setDerrotas(0);
            reporte.setGoles(0);
            reporte.setGolesEnContra(0);
            reporte.setDiferenciaGol(0);
            reporte.setPuntos(0);

            reportes.put(categoriaId, reporte);
        }

        agregarPartidoCategoria(reporte, partido);
    }

    for (ReporteCategoriaResponseDto reporte : reportes.values()) {
        calcularPorcentajesCategoria(reporte);
    }

    return reportes.values().stream().toList();
}
public ReporteCategoriaResponseDto obtenerReporteCategoriaPorCampeonato(
        Long categoriaId,
        Long campeonatoId) {

    List<Partido> partidos =
            partidoRepository.findByCampeonatoId(campeonatoId);

    ReporteCategoriaResponseDto reporte = null;

    for (Partido partido : partidos) {

        if (partido.getCategoria() == null) {
            continue;
        }

        if (!partido.getCategoria().getId().equals(categoriaId)) {
            continue;
        }

        if (reporte == null) {
            reporte = crearReporteCategoria(partido);
        }

        agregarPartidoCategoria(reporte, partido);
    }

    if (reporte == null) {
        throw new RecursoNoEncontradoException(
                "No se encontraron partidos para la categoria con id "
                        + categoriaId
                        + " en el campeonato "
                        + campeonatoId
        );
    }

    calcularPorcentajesCategoria(reporte);

    return reporte;
}


public List<ReporteCategoriaResponseDto> obtenerReporteCategoriasPorPartido(
        Long partidoId) {

    Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Partido con id " + partidoId + " no encontrado"
                    )
            );

    if (partido.getCategoria() == null) {
        return List.of();
    }

    ReporteCategoriaResponseDto reporte =
            new ReporteCategoriaResponseDto();

    reporte.setCategoriaId(partido.getCategoria().getId());

    reporte.setCategoriaNombre(
            partido.getCategoria().getNombre()
    );

    reporte.setPartidos(0);
    reporte.setVictorias(0);
    reporte.setEmpates(0);
    reporte.setDerrotas(0);
    reporte.setGoles(0);
    reporte.setGolesEnContra(0);
    reporte.setDiferenciaGol(0);
    reporte.setPuntos(0);

    agregarPartidoCategoria(reporte, partido);

    calcularPorcentajesCategoria(reporte);

    return List.of(reporte);
}
public ReporteCategoriaResponseDto obtenerReporteCategoriaPorPartido(
        Long categoriaId,
        Long partidoId) {

    Partido partido = partidoRepository.findById(partidoId)
            .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                            "Partido con id "
                                    + partidoId
                                    + " no encontrado"
                    )
            );

    if (partido.getCategoria() == null ||
            !partido.getCategoria().getId().equals(categoriaId)) {

        throw new RecursoNoEncontradoException(
                "El partido no pertenece a la categoria con id "
                        + categoriaId
        );
    }

    ReporteCategoriaResponseDto reporte =
            crearReporteCategoria(partido);

    agregarPartidoCategoria(reporte, partido);

    calcularPorcentajesCategoria(reporte);

    return reporte;
}
private ReporteCategoriaResponseDto crearReporteCategoria(
        Partido partido) {

    ReporteCategoriaResponseDto reporte =
            new ReporteCategoriaResponseDto();

    reporte.setCategoriaId(
            partido.getCategoria().getId()
    );

    reporte.setCategoriaNombre(
            partido.getCategoria().getNombre()
    );

    reporte.setPartidos(0);
    reporte.setVictorias(0);
    reporte.setEmpates(0);
    reporte.setDerrotas(0);
    reporte.setGoles(0);
    reporte.setGolesEnContra(0);
    reporte.setDiferenciaGol(0);
    reporte.setPuntos(0);

    return reporte;
}

private void agregarPartidoEntrenador(
        ReporteEntrenadorResponseDto reporte,
        Partido partido) {

    int[] goles = obtenerGolesPartido(partido);

    int golesFavor = goles[0];
    int golesContra = goles[1];

    reporte.setPartidos(
            reporte.getPartidos() + 1
    );

    reporte.setGoles(
            reporte.getGoles() + golesFavor
    );

    reporte.setGolesEnContra(
            reporte.getGolesEnContra() + golesContra
    );

    reporte.setDiferenciaGol(
            reporte.getGoles() - reporte.getGolesEnContra()
    );

    if (golesFavor > golesContra) {

        reporte.setVictorias(
                reporte.getVictorias() + 1
        );

        reporte.setPuntos(
                reporte.getPuntos() + 3
        );

    } else if (golesFavor == golesContra) {

        reporte.setEmpates(
                reporte.getEmpates() + 1
        );

        reporte.setPuntos(
                reporte.getPuntos() + 1
        );

    } else {

        reporte.setDerrotas(
                reporte.getDerrotas() + 1
        );
    }
}


private void calcularPorcentajesEntrenador(
        ReporteEntrenadorResponseDto reporte) {

    int partidos = reporte.getPartidos();

    if (partidos == 0) {
        reporte.setPorcentajeVictorias(0.0);
        reporte.setPorcentajeEmpates(0.0);
        reporte.setPorcentajeDerrotas(0.0);
        reporte.setEfectividad(0.0);
        reporte.setPromedioGoles(0.0);
        reporte.setPromedioGolesContra(0.0);
        return;
    }

    reporte.setPorcentajeVictorias(
            redondear(
                    reporte.getVictorias() * 100.0 / partidos
            )
    );

    reporte.setPorcentajeEmpates(
            redondear(
                    reporte.getEmpates() * 100.0 / partidos
            )
    );

    reporte.setPorcentajeDerrotas(
            redondear(
                    reporte.getDerrotas() * 100.0 / partidos
            )
    );

    reporte.setEfectividad(
            redondear(
                    reporte.getPuntos() * 100.0 / (partidos * 3)
            )
    );

    reporte.setPromedioGoles(
            redondear(
                    reporte.getGoles() * 1.0 / partidos
            )
    );

    reporte.setPromedioGolesContra(
            redondear(
                    reporte.getGolesEnContra() * 1.0 / partidos
            )
    );
}

private void agregarPartidoCategoria(
        ReporteCategoriaResponseDto reporte,
        Partido partido) {

    int[] goles = obtenerGolesPartido(partido);

    int golesFavor = goles[0];
    int golesContra = goles[1];

    reporte.setPartidos(
            reporte.getPartidos() + 1
    );

    reporte.setGoles(
            reporte.getGoles() + golesFavor
    );

    reporte.setGolesEnContra(
            reporte.getGolesEnContra() + golesContra
    );

    reporte.setDiferenciaGol(
            reporte.getGoles() - reporte.getGolesEnContra()
    );

    if (golesFavor > golesContra) {

        reporte.setVictorias(
                reporte.getVictorias() + 1
        );

        reporte.setPuntos(
                reporte.getPuntos() + 3
        );

    } else if (golesFavor == golesContra) {

        reporte.setEmpates(
                reporte.getEmpates() + 1
        );

        reporte.setPuntos(
                reporte.getPuntos() + 1
        );

    } else {

        reporte.setDerrotas(
                reporte.getDerrotas() + 1
        );
    }
}


private void calcularPorcentajesCategoria(
        ReporteCategoriaResponseDto reporte) {

    int partidos = reporte.getPartidos();

    if (partidos == 0) {
        reporte.setPorcentajeVictorias(0.0);
        reporte.setPorcentajeEmpates(0.0);
        reporte.setPorcentajeDerrotas(0.0);
        reporte.setEfectividad(0.0);
        reporte.setPromedioGoles(0.0);
        reporte.setPromedioGolesContra(0.0);
        return;
    }

    reporte.setPorcentajeVictorias(
            redondear(
                    reporte.getVictorias() * 100.0 / partidos
            )
    );

    reporte.setPorcentajeEmpates(
            redondear(
                    reporte.getEmpates() * 100.0 / partidos
            )
    );

    reporte.setPorcentajeDerrotas(
            redondear(
                    reporte.getDerrotas() * 100.0 / partidos
            )
    );

    reporte.setEfectividad(
            redondear(
                    reporte.getPuntos() * 100.0 / (partidos * 3)
            )
    );

    reporte.setPromedioGoles(
            redondear(
                    reporte.getGoles() * 1.0 / partidos
            )
    );

    reporte.setPromedioGolesContra(
            redondear(
                    reporte.getGolesEnContra() * 1.0 / partidos
            )
    );
}
private int[] obtenerGolesPartido(Partido partido) {

    if (partido.getResultado() == null ||
            partido.getResultado().isBlank()) {

        return new int[]{0, 0};
    }

    String resultado = partido.getResultado().trim();

    String[] partes = resultado.split("-");

    if (partes.length != 2) {
        return new int[]{0, 0};
    }

    try {

        int primerNumero =
                Integer.parseInt(partes[0].trim());

        int segundoNumero =
                Integer.parseInt(partes[1].trim());

        if (Boolean.TRUE.equals(partido.getLocal())) {

            return new int[]{
                    primerNumero,
                    segundoNumero
            };

        } else {

            return new int[]{
                    segundoNumero,
                    primerNumero
            };
        }

    } catch (NumberFormatException e) {

        return new int[]{0, 0};
    }
}
private Double redondear(Double valor) {

    return Math.round(valor * 100.0) / 100.0;
}
}




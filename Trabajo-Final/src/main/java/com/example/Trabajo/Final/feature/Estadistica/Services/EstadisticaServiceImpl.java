package com.example.Trabajo.Final.feature.Estadistica.Services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Trabajo.Final.feature.Estadistica.Dtos.Request.EstadisticaRequestDto;
import com.example.Trabajo.Final.feature.Estadistica.Dtos.Response.EstadisticaResponseDto;
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
        nuevaEstadistica.setPartido(partido);
        nuevaEstadistica.setMinutos(dto.getMinutos());
        nuevaEstadistica.setGoles(dto.getGoles());
        nuevaEstadistica.setAsistencias(dto.getAsistencias());
        nuevaEstadistica.setRojas(dto.getRojas());
        nuevaEstadistica.setAmarillas((dto.getAmarillas()));
        nuevaEstadistica.setTitular(dto.getTitular());
        Estadistica guardado = estadisticaRepository.save(nuevaEstadistica);

        
        EstadisticaResponseDto respuesta = new EstadisticaResponseDto();
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
}




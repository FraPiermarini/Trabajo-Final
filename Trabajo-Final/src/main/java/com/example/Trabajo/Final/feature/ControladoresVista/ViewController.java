package com.example.Trabajo.Final.feature.ControladoresVista;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;





@Controller 

public class ViewController { 

    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }
    @GetMapping("/login") 
    public String login() { 
        return "login"; 
    } 
    @GetMapping("/register")
    public String register(){
        return "register";
    }
     @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("currentPage", "dashboard");

        return "dashboard";
    }

    @GetMapping("/jugadores")
    public String jugadores(Model model) {

        model.addAttribute("pageTitle", "Jugadores");
        model.addAttribute("currentPage", "jugadores");

        return "jugadores/list";
    }

    @GetMapping("/jugadores/nuevo")
    public String nuevoJugador(Model model) {

        model.addAttribute("pageTitle", "Nuevo jugador");
        model.addAttribute("currentPage", "jugadores");

        return "jugadores/form";
    }

    @GetMapping("/categorias")
    public String categorias(Model model) {

        model.addAttribute("pageTitle", "Categorías");
        model.addAttribute("currentPage", "categorias");

        return "categorias/list";
    }
    @GetMapping("/categorias/nuevo")
    public String nuevaCategoria(Model model) {
        model.addAttribute("pageTitle", "Nueva categoria");
        model.addAttribute("currentPage", "categorias");
        return "categorias/form";
    }

    @GetMapping("/entrenadores")
    public String entrenadores(Model model) {

        model.addAttribute("pageTitle", "Entrenadores");
        model.addAttribute("currentPage", "entrenadores");

        return "entrenadores/list";
    }

    @GetMapping("/entrenadores/nuevo")
    public String nuevoEntrenador(Model model){
        model.addAttribute("pageTitle", "Nuevo Entrenador");
        model.addAttribute("currentPage", "entrenadores");
        return "entrenadores/form";
    }

    @GetMapping("/partidos")
    public String partidos(Model model) {

        model.addAttribute("pageTitle", "Partidos");
        model.addAttribute("currentPage", "partidos");

        return "partidos/list";
    }

    @GetMapping("/partidos/nuevo")
    public String nuevoPartido(Model model){
        model.addAttribute("pageTitle", "Nuevo partido");
        model.addAttribute("currentPage", "partidos");
        return "partidos/form";
    }

    @GetMapping("/campeonatos")
    public String campeonatos(Model model) {

        model.addAttribute("pageTitle", "Campeonatos");
        model.addAttribute("currentPage", "campeonatos");

        return "campeonatos/list";
    }

    @GetMapping("/campeonatos/nuevo")
    public String nuevoCampeonato(Model model){
        model.addAttribute("pageTitle", "Nuevo campeonato");
        model.addAttribute("currentPage", "campeonatos");
        return "campeonatos/form";
    }

    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {

        model.addAttribute("pageTitle", "Estadísticas");
        model.addAttribute("currentPage", "estadisticas");

        return "estadisticas/list";
    }

    @GetMapping("/estadisticas/nuevo")
    public String nuevaEstadistica(Model model){
        model.addAttribute("pageTitle", "Nueva estadistica");
        model.addAttribute("currentPage", "estadisticas");
        return "estadisticas/form";
    }

    @GetMapping("/incidencias")
    public String incidencias(Model model) {

        model.addAttribute("pageTitle", "Incidencias");
        model.addAttribute("currentPage", "incidencias");

        return "incidencias/list";
    }
    @GetMapping("incidencias/nuevo")
    public String nuevaIncidencia(Model model){
        model.addAttribute("pageTitle", "Nueva incidencia");
        model.addAttribute("currentPage", "incidencias");
        return "incidencias/form";
    }

    @GetMapping("/reportes")
    public String reportes(Model model) {

        model.addAttribute("pageTitle", "Reportes");
        model.addAttribute("currentPage", "reportes");

        return "reportes/list";
    }

    @GetMapping("/configuracion")
    public String configuracion(Model model) {

        model.addAttribute("pageTitle", "Configuración");
        model.addAttribute("currentPage", "configuracion");

        return "configuracion/list";
    }
}


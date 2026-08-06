package com.example.Trabajo.Final.feature.ControladoresVista;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {


    @GetMapping("/")
    public String inicio(){

        return "index";

    }

}
package com.example.Trabajo.Final.feature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioViewController {


    @GetMapping("/registro")
    public String registro(){

        return "registro";

    }

}
package com.example.Trabajo.Final.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Trabajo.Final.feature.Usuario.Models.Rol;
import com.example.Trabajo.Final.feature.Usuario.Models.Usuario;
import com.example.Trabajo.Final.feature.Usuario.Repositories.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    


    @Override
    public void run(String... args){
        if(usuarioRepository.findByEmail("admin@admin.com").isEmpty()){
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellido("Admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRol(Rol.ADMIN);
            usuarioRepository.save(admin);
        }
        if(usuarioRepository.findByEmail("entrenador@entrenador.com").isEmpty()){
            Usuario entrenador = new Usuario();
            entrenador.setNombre("Entrenador");
            entrenador.setApellido("Entrenador");
            entrenador.setEmail("entrenador@entrenador.com");
            entrenador.setPassword(passwordEncoder.encode("entrenador"));
            entrenador.setRol(Rol.ENTRENADOR);
            usuarioRepository.save(entrenador);
        }
    }
}

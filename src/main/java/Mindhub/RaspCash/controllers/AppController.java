package Mindhub.RaspCash.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Mindhub.RaspCash.servicios.ServicioUsuario;

import javax.mail.MessagingException;

@RestController
public class AppController {

    @Autowired 
    ServicioUsuario servicioUsuario;


    
    @PostMapping("/api/usuarios")
    public ResponseEntity<Object> register(
            @RequestParam String nombre, @RequestParam String apellido, @RequestParam String apodo,
            @RequestParam String correo, @RequestParam String password) throws MessagingException {

        return servicioUsuario.registro(nombre, apellido, apodo, correo, password);
    }
}

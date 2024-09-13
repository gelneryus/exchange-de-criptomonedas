package Mindhub.RaspCash.controllers;

import Mindhub.RaspCash.servicios.ServicioUsuario;
import Mindhub.excepciones.ConflictException;
import Mindhub.RaspCash.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    ServicioUsuario servicioUsuario;

    @GetMapping("/usuarios")
    public List<UsuarioDTO> obtenerUsuarios(){
        return servicioUsuario.obtenerUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable long id)  {
    	return servicioUsuario.obtenerUsuarioPorId(id);
    }

    @GetMapping("/usuarios/current")
    public UsuarioDTO obtenerUsuarioActual(Authentication authentication){
       UsuarioDTO usuario = servicioUsuario.findByEmail(authentication.getName());
       
       if(usuario == null)
            throw new ConflictException("No se encontro ningún usuario logueado");
       
        return usuario;
    }

}

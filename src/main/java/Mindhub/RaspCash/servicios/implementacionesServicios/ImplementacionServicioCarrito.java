package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.dtos.CarritoDTO;
import Mindhub.RaspCash.models.Carrito;
import Mindhub.RaspCash.models.Usuario;
import Mindhub.RaspCash.respositories.CarritoRepositorio;
import Mindhub.RaspCash.respositories.UsuarioRepositorio;
import Mindhub.RaspCash.servicios.ServicioCarrito;
import Mindhub.RaspCash.servicios.ServicioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioCarrito implements ServicioCarrito {

    @Autowired
    CarritoRepositorio carritoRepositorio;

    @Autowired
    ServicioUsuario servicioUsuario;

    @Override
    public CarritoDTO obtenerCarritoDTOPorId(long id) {
        //accountRepository.findById(id).map(AccountDTO::new).orElse(null);
        return carritoRepositorio.findById(id).map(CarritoDTO::new).orElse(null);
    }

    @Override
    public Carrito obtenerCarritoPorId(long id) {
        return carritoRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<CarritoDTO> obtenerTodosLosCarritos() {
        return carritoRepositorio.findAll().stream().map(CarritoDTO::new).collect(Collectors.toList());
    }

    @Override 
    public CarritoDTO obtenerCurrentCarrito(Authentication authentication){
        //Hacer excepciones
        Usuario usuario = servicioUsuario.encontrarUsuarioPorEmail(authentication.getName());
        return new CarritoDTO(usuario.getCarrito());
    }

    @Override
    public void guardarCarrito(Carrito carrito) {
        carritoRepositorio.save(carrito);
    }

    @Override
    public void borrarCarrito(Carrito carrito) {
        carritoRepositorio.delete(carrito);
    }


}

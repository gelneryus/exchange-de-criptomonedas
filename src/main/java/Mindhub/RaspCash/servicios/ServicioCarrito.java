package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.dtos.CarritoDTO;
import Mindhub.RaspCash.models.Carrito;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioCarrito {

    public CarritoDTO obtenerCarritoDTOPorId(long id);
    public Carrito obtenerCarritoPorId(long id);
    public List<CarritoDTO> obtenerTodosLosCarritos();
    public CarritoDTO obtenerCurrentCarrito(Authentication authentication);
    public void guardarCarrito(Carrito carrito);
    public void borrarCarrito(Carrito carrito);
}

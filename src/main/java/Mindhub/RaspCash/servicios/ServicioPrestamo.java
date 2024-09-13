package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.dtos.PrestamoDTO;
import Mindhub.RaspCash.models.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPrestamo {

    public List<PrestamoDTO> obtenerPrestamos();
    public void guardarPrestamo(Prestamo prestamo);
    public Prestamo encontrarPrestamoPorNombre(String nombre);
}

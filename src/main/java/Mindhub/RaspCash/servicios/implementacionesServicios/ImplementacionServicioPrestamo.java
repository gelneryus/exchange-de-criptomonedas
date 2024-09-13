package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.dtos.PrestamoDTO;
import Mindhub.RaspCash.models.Prestamo;
import Mindhub.RaspCash.respositories.PrestamoRespositorio;
import Mindhub.RaspCash.servicios.ServicioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioPrestamo implements ServicioPrestamo {

    @Autowired
    PrestamoRespositorio prestamoRespositorio;

    @Override
    public List<PrestamoDTO> obtenerPrestamos() {
        return prestamoRespositorio.findAll().stream().map(PrestamoDTO::new).collect(Collectors.toList());
    }

    @Override
    public void guardarPrestamo(Prestamo prestamo) {
        prestamoRespositorio.save(prestamo);
    }

    @Override
    public Prestamo encontrarPrestamoPorNombre(String nombre) {
        return prestamoRespositorio.findByNombre(nombre);
    }


}

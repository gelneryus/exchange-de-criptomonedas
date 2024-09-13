package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.dtos.BilleteraDTO;
import Mindhub.RaspCash.models.Billetera;
import Mindhub.RaspCash.respositories.BilleteraRepositorio;
import Mindhub.RaspCash.servicios.ServicioBilletera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioBilletera implements ServicioBilletera {

    @Autowired
    BilleteraRepositorio billeteraRepositorio;

    @Override
    public Billetera encontrarPorDireccion(String direccion) {
        return billeteraRepositorio.findByDireccion(direccion);
    }

    @Override
    public List<BilleteraDTO> obtenerTodasLasBilleteras() {
        return billeteraRepositorio.findAll().stream().map(BilleteraDTO::new).collect(Collectors.toList());
    }
}

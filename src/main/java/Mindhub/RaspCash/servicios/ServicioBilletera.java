package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.dtos.BilleteraDTO;
import Mindhub.RaspCash.models.Billetera;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioBilletera {
    public Billetera encontrarPorDireccion(String direccion);
    public List<BilleteraDTO> obtenerTodasLasBilleteras();
}

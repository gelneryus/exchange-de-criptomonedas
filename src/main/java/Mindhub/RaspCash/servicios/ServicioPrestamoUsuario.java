package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.models.PrestamoUsuario;
import org.springframework.stereotype.Service;

@Service
public interface ServicioPrestamoUsuario {

    public void guardarPrestamoUsuario(PrestamoUsuario prestamoUsuario);
}

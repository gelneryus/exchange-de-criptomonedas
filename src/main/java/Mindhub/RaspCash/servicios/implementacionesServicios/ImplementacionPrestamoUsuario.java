package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.models.PrestamoUsuario;
import Mindhub.RaspCash.respositories.PrestamoUsuarioRespositorio;
import Mindhub.RaspCash.servicios.ServicioPrestamoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionPrestamoUsuario implements ServicioPrestamoUsuario {

    @Autowired
    PrestamoUsuarioRespositorio prestamoUsuarioRespositorio;

    @Override
    public void guardarPrestamoUsuario(PrestamoUsuario prestamoUsuario) {
        prestamoUsuarioRespositorio.save(prestamoUsuario);
    }
}

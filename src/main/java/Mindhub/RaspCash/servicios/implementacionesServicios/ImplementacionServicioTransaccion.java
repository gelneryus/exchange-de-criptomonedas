package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.models.Transaccion;
import Mindhub.RaspCash.respositories.TransaccionRepositorio;
import Mindhub.RaspCash.servicios.ServicioTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioTransaccion implements ServicioTransaccion {

    @Autowired
    TransaccionRepositorio transaccionRepositorio;

    @Override
    public void guardarTransaccion(Transaccion transaccion) {
        transaccionRepositorio.save(transaccion);
    }
}

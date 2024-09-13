package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.models.Transaccion;
import org.springframework.stereotype.Service;

@Service
public interface ServicioTransaccion  {
    public void guardarTransaccion(Transaccion transaccion);
}

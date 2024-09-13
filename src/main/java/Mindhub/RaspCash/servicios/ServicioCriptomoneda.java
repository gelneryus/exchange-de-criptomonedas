package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.dtos.CriptoMonedaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioCriptomoneda {

    public List<CriptoMonedaDTO> obtenerTodasLasCriptomonedas();
    
    public List<CriptoMonedaDTO> obtenerCriptoMonedasPorNombre(String nombre);
    
    public List<CriptoMonedaDTO> filtrarCriptoMonedasPorCotizacion(double precioMinimo, double precioMaximo);
    
    public ResponseEntity<Object> agregarCriptoMoneda(CriptoMonedaDTO criptoMonedaDto);
}

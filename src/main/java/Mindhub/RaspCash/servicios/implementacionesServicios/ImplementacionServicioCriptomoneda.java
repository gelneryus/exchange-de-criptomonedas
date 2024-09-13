package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.dtos.CriptoMonedaDTO;
import Mindhub.RaspCash.models.CriptoMoneda;
import Mindhub.RaspCash.respositories.CriptoMonedaRepositorio;
import Mindhub.RaspCash.servicios.ServicioCriptomoneda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioCriptomoneda implements ServicioCriptomoneda {

    @Autowired
    CriptoMonedaRepositorio criptoMonedaRepositorio;

    @Override
    public List<CriptoMonedaDTO> obtenerTodasLasCriptomonedas() {
        return criptoMonedaRepositorio.findAll().stream().map(CriptoMonedaDTO::new).collect(Collectors.toList());
    }

	@Override
	public List<CriptoMonedaDTO> obtenerCriptoMonedasPorNombre(String nombre) {
		List<CriptoMoneda> criptoMonedas = criptoMonedaRepositorio.buscarCriptoMonedasPorNombre(nombre);
		
		return criptoMonedas.stream().map(CriptoMonedaDTO::new).collect(Collectors.toList());
	}

	@Override
	public List<CriptoMonedaDTO> filtrarCriptoMonedasPorCotizacion(double precioMinimo, double precioMaximo) {
		List<CriptoMoneda> criptoMonedas = criptoMonedaRepositorio.filtrarCriptoMonedasPorCotizacion(precioMinimo, precioMaximo);
		return criptoMonedas.stream().map(CriptoMonedaDTO::new).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<Object> agregarCriptoMoneda(CriptoMonedaDTO criptoMonedaDto) {
		//Agregar excepciones
		criptoMonedaRepositorio.save(new CriptoMoneda(criptoMonedaDto.getNombre(),criptoMonedaDto.getSimboloCriptomoneda(), criptoMonedaDto.getCotizacion(), criptoMonedaDto.getLogo()));
		return new ResponseEntity<>("Criptomoneda agregada con exito", HttpStatus.CREATED);
	}
}

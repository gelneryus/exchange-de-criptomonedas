package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.CriptoMoneda;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CriptoMonedaRepositorio extends JpaRepository<CriptoMoneda,Long> {

	@Query(value = "select c from CriptoMoneda c "
			+ " where c.nombre LIKE %:nombre% ")
	List<CriptoMoneda> buscarCriptoMonedasPorNombre(@Param("nombre") String nombre);

	@Query(value = "select c from CriptoMoneda c "
			+ " where c.cotizacion <= :precioMaximo "
			+ " and c.cotizacion >= :precioMinimo ")
	List<CriptoMoneda> filtrarCriptoMonedasPorCotizacion(@Param("precioMinimo") double precioMinimo, @Param("precioMaximo") double precioMaximo);
}

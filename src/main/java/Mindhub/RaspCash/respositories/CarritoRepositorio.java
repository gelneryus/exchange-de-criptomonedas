package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarritoRepositorio extends JpaRepository<Carrito,Long> {

}

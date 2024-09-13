package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoRepositorio extends JpaRepository<Producto,Long> {

}

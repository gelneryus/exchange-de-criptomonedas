package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.ProductoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoUsuarioRepositorio extends JpaRepository<ProductoUsuario,Long> {
    ProductoUsuario getById(long id);
}

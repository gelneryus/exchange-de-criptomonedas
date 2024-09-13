package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransaccionRepositorio extends JpaRepository<Transaccion,Long> {

}

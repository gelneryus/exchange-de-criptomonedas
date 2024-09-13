package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.PrestamoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PrestamoUsuarioRespositorio extends JpaRepository<PrestamoUsuario,Long> {

}

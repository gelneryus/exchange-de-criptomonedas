package Mindhub.RaspCash.respositories;

import Mindhub.RaspCash.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
	
	@Query(value = "select u from Usuario u "
			+ "where u.email = :email")
    Usuario findByEmail(@Param("email") String email);
	
	@Query("select u from Usuario u where u.id = :id")
	Usuario buscarPorId(@Param("id") long id);
}

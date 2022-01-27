package pe.cine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.cine.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);
	@Query("select usuario "
			+ "from Usuario usuario "
			+ "join usuario.roles rol "
			+ "where rol.rolNombre = 'ROLE_ADMIN' Order By usuario.usuario_id DESC")
	List<Usuario> lista();
	
	@Query(value = "select * from usuario where email = ?1",nativeQuery = true)
	Usuario buscarporemail(String email);
	
	
}

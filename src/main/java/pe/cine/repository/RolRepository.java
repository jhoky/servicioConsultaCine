package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.enums.RolNombre;
import pe.cine.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	boolean existsByRolNombre(RolNombre rolNombre);
	

}

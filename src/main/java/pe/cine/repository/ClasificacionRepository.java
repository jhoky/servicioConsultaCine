package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Clasificacion;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer> {
	
	Optional<Clasificacion> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

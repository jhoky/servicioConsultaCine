package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer>{
	
	Optional<Distrito> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

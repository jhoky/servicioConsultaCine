package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
	
	Optional<Director> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

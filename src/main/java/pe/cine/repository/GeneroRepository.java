package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
	
	Optional<Genero> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

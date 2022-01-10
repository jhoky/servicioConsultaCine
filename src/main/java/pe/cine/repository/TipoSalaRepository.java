package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Tipo_Sala;

@Repository
public interface TipoSalaRepository extends JpaRepository<Tipo_Sala, Integer> {
	
	Optional<Tipo_Sala> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

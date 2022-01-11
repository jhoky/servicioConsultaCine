package pe.cine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Tipo_Publico;

@Repository
public interface TipoPublicoRepository extends JpaRepository<Tipo_Publico, Integer> {
	
	Optional<Tipo_Publico> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

package pe.cine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.cine.model.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
	
	Optional<Dia> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
	@Query(value = "SELECT * FROM dia ORDER BY dia_id ASC",nativeQuery = true)
    List<Dia> listAsc();

}

package pe.cine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.cine.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
	
	Optional<Director> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
	@Query(value="SELECT * FROM director ORDER BY director_id DESC", nativeQuery=true)
	List<Director> listDesc();
	
	@Query(value="SELECT * FROM director ORDER BY nombre ASC", nativeQuery=true)
	List<Director> listNombreAsc();

}

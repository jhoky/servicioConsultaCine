package pe.cine.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.cine.model.Cine;

@Repository
public interface CineRepository extends JpaRepository<Cine, Integer>{
	
	Optional<Cine> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
	
	@Query("SELECT MAX(cine_id) AS id FROM Cine")
	int obtenerId();
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM cine_sede WHERE fk_cine_id= :cine AND fk_sede_id= :sede",nativeQuery = true)
	public void deleteCinSed(@Param("cine") int cine,@Param("sede") int sede);
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM cine_pelicula WHERE fk_cine_id= :cine AND fk_pelicula_id= :pelicula",nativeQuery = true)
	public void deleteCinPel(@Param("cine") int cine,@Param("pelicula") int pelicula);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO cine_sede(fk_cine_id, fk_sede_id) VALUES (:cine, :sede)",nativeQuery = true)
	public void insertCinSed(@Param("cine") int cine,@Param("sede") int sede);
    
    @Modifying
    @Transactional
    @Query(value="INSERT INTO cine_pelicula(fk_cine_id, fk_pelicula_id) VALUES (:cine, :pelicula)",nativeQuery = true)
	public void insertCinPel(@Param("cine") int cine,@Param("pelicula") int pelicula);
    
    @Query(value="SELECT fk_cine_id,fk_sede_id FROM cine_sede", nativeQuery=true)
	List<?> listarCinSed();
    
    @Query(value="SELECT fk_cine_id,fk_pelicula_id FROM cine_pelicula", nativeQuery=true)
	List<?> listarCinPel();
	
	@Query(value="SELECT * FROM cine WHERE estado=true ORDER BY cine.nombre ASC", nativeQuery=true)
	List<Cine> listActivos();
	
}

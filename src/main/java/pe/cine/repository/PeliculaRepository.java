
package pe.cine.repository;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.cine.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	
	Optional<Pelicula> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    @Query("SELECT MAX(pelicula_id) AS id FROM Pelicula")
	int obtenerId();


    //listado de la cartelera
    @Query(value = "select*from pelicula order by fecha_estreno desc limit 4",nativeQuery = true)
    ArrayList<Pelicula> listarCartelera();
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM pelicula_genero WHERE fk_pelicula_id= :pelicula AND fk_genero_id= :genero",nativeQuery = true)
	public void deletePelGen(@Param("pelicula") int pelicula,@Param("genero") int genero);
}

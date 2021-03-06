package pe.cine.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.cine.dto.SedeTipoSala;
import pe.cine.model.Sede;
import pe.cine.model.Tipo_Sala;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
	
	Optional<Sede> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
    @Query("SELECT MAX(sede_id) AS id FROM Sede")
	int obtenerId();
	
	@Modifying
    @Transactional
    @Query(value="DELETE FROM sede_tipo_sala WHERE sede_id= :sede AND tipo_sala_id= :sala",nativeQuery = true)
	public void deleteSedSal(@Param("sede") int sede,@Param("sala") int sala);
    
    @Query(value = "select tp.nombre from sede_tipo_sala st inner join tipo_sala tp on st.tipo_sala_id = tp.tipo_sala_id inner join sede s on st.sede_id = s.sede_id  where s.sede_id = ?1",nativeQuery = true)
    ArrayList<String> buscartiposala(int idsede);
	
	@Modifying
    @Transactional
    @Query(value="INSERT INTO sede_tipo_sala(sede_id, tipo_sala_id) VALUES (:sede, :sala)",nativeQuery = true)
	public void insertSedSal(@Param("sede") int sede,@Param("sala") int sala);
    
    @Query(value="SELECT sede_id, tipo_sala_id FROM sede_tipo_sala", nativeQuery=true)
	List<?> listarSedSal();
	
	@Query(value="SELECT * FROM sede WHERE estado=true ORDER BY sede.nombre ASC", nativeQuery=true)
	List<Sede> listActivos();
    
    @Query(value="SELECT * FROM sede ORDER BY sede_id DESC", nativeQuery=true)
	List<Sede> listDesc();
    

}

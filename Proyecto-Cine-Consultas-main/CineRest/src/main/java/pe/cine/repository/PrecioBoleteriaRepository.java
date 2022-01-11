package pe.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.cine.model.Precio_Boleteria;

@Repository
public interface PrecioBoleteriaRepository extends JpaRepository<Precio_Boleteria, Integer> {
  
  @Query(value="SELECT * FROM Precio_Boleteria p WHERE p.fk_cine_id= :cine AND p.fk_dia_id= :dia AND p.fk_tipo_sala_id= :sala",nativeQuery = true)
	public List<Precio_Boleteria> buscarFiltro(@Param("cine") int cine,@Param("dia") int dia, @Param("sala") int sala);
	
	

	@Query(value = "select*from precio_boleteria where fk_cine_id=:cine_id order by fk_cine_id",nativeQuery = true )
	public List<Precio_Boleteria> buscarprecioporcine(@Param("cine_id") int cine_id);
	
	

}

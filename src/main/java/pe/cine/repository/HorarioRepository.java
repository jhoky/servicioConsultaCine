package pe.cine.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.cine.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
	
	
	@Query(value="SELECT * FROM Horario h WHERE h.cine_id= :cine AND h.fk_pelicula_id= :pelicula AND h.fecha= :fech",nativeQuery = true)
	public List<Horario> buscarFiltro(@Param("cine") int cine,@Param("pelicula") int pelicula, @Param("fech") Date fech);
	
	@Query(value = "select h.horario_id, h.cine_id, h.estado,h.fecha,h.hora_inicio,h.fk_pelicula_id from pelicula p inner join horario h on p.pelicula_id = h.fk_pelicula_id where p.pelicula_id = ?1  and h.cine_id = ?2",nativeQuery = true)
	ArrayList<Horario> listahorariosporPelicula(int idpel, int idcine);
	
	@Query(value="SELECT hora_inicio FROM horario WHERE cine_id= :cine AND fk_pelicula_id= :pelicula AND fecha= :fecha AND hora_inicio= :hora", nativeQuery=true)
	List<?> listarHoras(@Param("cine") int cine, @Param("pelicula") int pelicula, @Param("fecha") LocalDate fecha, @Param("hora") String hora);


}

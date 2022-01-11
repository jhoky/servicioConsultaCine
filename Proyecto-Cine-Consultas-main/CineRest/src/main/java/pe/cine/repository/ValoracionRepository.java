package pe.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {

}

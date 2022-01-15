package pe.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.CodigoDescuento;

@Repository
public interface DescuentoRepository extends JpaRepository<CodigoDescuento, Integer>{

}

package pe.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cine.model.CodigoDescuento;

@Repository
public interface DescuentoRepository extends JpaRepository<CodigoDescuento, Integer>{

  @Query(value = "select cd.descuento_id,cd.nr_descuento,cd.usuario_id from usuario u inner join codigo_descuento cd on u.usuario_id = cd.usuario_id where u.email = ?1",nativeQuery = true)
    CodigoDescuento buscarcodigodescuento(String email);

  
}

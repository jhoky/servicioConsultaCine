package pe.cine.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.CodigoDescuento;
import pe.cine.service.DescuentoService;

@RestController
@RequestMapping("/descuento")
@CrossOrigin(origins = "http://localhost:4200")
public class DescuentoController {
	
	@Autowired
	private DescuentoService serv;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Códigos de Descuento",httpMethod = "GET",nickname = "Listar Códigos Descuentos")
    public ResponseEntity<List<CodigoDescuento>> list(){
        List<CodigoDescuento> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Código de Descuento Según el ID",httpMethod = "GET",nickname = "Detalle Código Descuento")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        CodigoDescuento codigoDescuento = serv.getOne(id).get();
        return new ResponseEntity<>(codigoDescuento, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Código de Descuento",httpMethod = "POST",nickname = "Registro Código Descuento")
    public ResponseEntity<?> create(@Valid @RequestBody CodigoDescuento codigoDescuento){
    	if(codigoDescuento.getNr_descuento()<=0)
    		return new ResponseEntity<>(new Mensaje("El Número Debe Ser Mayor a 0"), HttpStatus.BAD_REQUEST);
        serv.save(codigoDescuento);
        return new ResponseEntity<>(new Mensaje("Código de Descuento Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Código de Descuento",httpMethod = "PUT",nickname = "Actualizar Código Descuento")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody CodigoDescuento newCodigoDescuento){
    	if(newCodigoDescuento.getNr_descuento()<=0)
    		return new ResponseEntity<>(new Mensaje("El Número Debe Ser Mayor a 0"), HttpStatus.BAD_REQUEST);
        CodigoDescuento codigoDescuento = serv.getOne(id).get();
        codigoDescuento.setNr_descuento(newCodigoDescuento.getNr_descuento());
        codigoDescuento.setUsuario(newCodigoDescuento.getUsuario());
        serv.save(codigoDescuento);
        return new ResponseEntity<>(new Mensaje("Código de Descuento Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Código de Descuento",httpMethod = "DELETE",nickname = "Eliminar Código Descuento")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Código de Descuento Eliminado"), HttpStatus.OK);
    }
	
	
    @GetMapping("/buscarcodigo/{email}")
    @ApiOperation(value = "Detalle del Código de Descuento Según el ID",httpMethod = "GET",nickname = "Detalle Código Descuento")
    public ResponseEntity<?> buscarcodigodescuento(@PathVariable("email") String email){
    	
    	
    	Usuario usu = userv.buscarporemail(email);
    	
    	CodigoDescuento cd = serv.buscarcodigo(email);
    	
    	if (cd == null) {
			
			int aleatorio = (int) (10000 + Math.random() * 90000);
			CodigoDescuento cnuevo = new CodigoDescuento(aleatorio,usu);
			serv.save(cnuevo);
			return new ResponseEntity<>(cnuevo, HttpStatus.OK);				
		}
        
        return new ResponseEntity<>(cd, HttpStatus.OK);
    }	
	

}

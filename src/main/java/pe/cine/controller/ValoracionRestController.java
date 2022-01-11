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
import pe.cine.model.Valoracion;
import pe.cine.service.ValoracionService;

@RestController
@RequestMapping("/valoracion")
@CrossOrigin(origins = "http://localhost:4200")
public class ValoracionRestController {

	@Autowired
	private ValoracionService serv;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todas las Valoraciones",httpMethod = "GET",nickname = "Listar Valoraciones")
    public ResponseEntity<List<Valoracion>> list(){
        List<Valoracion> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de la Valoración Según el ID",httpMethod = "GET",nickname = "Detalle Valoración")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Valoracion valoracion = serv.getOne(id).get();
        return new ResponseEntity<>(valoracion, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro de la Valoración",httpMethod = "POST",nickname = "Registro Valoración")
    public ResponseEntity<?> create(@Valid @RequestBody Valoracion valoracion){
    	if(valoracion.getPuntuacion()<0 && valoracion.getPuntuacion()>5)
    		return new ResponseEntity<>(new Mensaje("La Valoración Se Realiza Del 1 al 5"), HttpStatus.BAD_REQUEST);
        serv.save(valoracion);
        return new ResponseEntity<>(new Mensaje("Valoracion Creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de la Valoración",httpMethod = "PUT",nickname = "Actualizar Valoración")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Valoracion newvaloracion){
    	if(newvaloracion.getPuntuacion()<0 && newvaloracion.getPuntuacion()>5)
    		return new ResponseEntity<>(new Mensaje("La Valoración Se Realiza Del 1 al 5"), HttpStatus.BAD_REQUEST);
        Valoracion valoracion = serv.getOne(id).get();
        valoracion.setPuntuacion(newvaloracion.getPuntuacion());
        valoracion.setSede(newvaloracion.getSede());
        valoracion.setUsuario(newvaloracion.getUsuario());
        serv.save(valoracion);
        return new ResponseEntity<>(new Mensaje("Valoración Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de la Valoración",httpMethod = "DELETE",nickname = "Eliminar Valoración")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Valoración Eliminada"), HttpStatus.OK);
    }

}

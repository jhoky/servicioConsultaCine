package pe.cine.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import pe.cine.model.Clasificacion;
import pe.cine.service.ClasificacionService;

@RestController
@RequestMapping("/clasificacion")
@CrossOrigin(origins = "http://localhost:4200")
public class ClasificacionRestController {

	@Autowired
	private ClasificacionService serv;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todas las Calificaciones",httpMethod = "GET",nickname = "Listar Calificaciones")
    public ResponseEntity<List<Clasificacion>> list(){
        List<Clasificacion> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de la Clasificación Según el ID",httpMethod = "GET",nickname = "Detalle Clasificación")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Clasificacion clasificacion = serv.getOne(id).get();
        return new ResponseEntity<>(clasificacion, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro de la Clasificación",httpMethod = "POST",nickname = "Registro Clasificación")
    public ResponseEntity<?> create(@Valid @RequestBody Clasificacion clasificacion){
    	if(StringUtils.isBlank(clasificacion.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(clasificacion.getNombre().length()>3)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(clasificacion.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(clasificacion);
        return new ResponseEntity<>(new Mensaje("Clasificación Creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de la Clasificación",httpMethod = "PUT",nickname = "Actualizar Clasificación")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Clasificacion newclasificacion){
    	if(StringUtils.isBlank(newclasificacion.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newclasificacion.getNombre().length()>3)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newclasificacion.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Clasificacion clasificacion = serv.getOne(id).get();
        clasificacion.setNombre(newclasificacion.getNombre());
        serv.save(clasificacion);
        return new ResponseEntity<>(new Mensaje("Clasificación Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de la Clasificación",httpMethod = "DELETE",nickname = "Eliminar Clasificación")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Clasificación Eliminada"), HttpStatus.OK);
    }
}

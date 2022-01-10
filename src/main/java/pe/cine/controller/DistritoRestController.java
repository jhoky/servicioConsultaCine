package pe.cine.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Distrito;
import pe.cine.service.DistritoService;

@Controller
@RequestMapping("/distrito")
@CrossOrigin(origins = "http://localhost:4200")
public class DistritoRestController {

	@Autowired
	private DistritoService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos Los Distritos",httpMethod = "GET",nickname = "Listar Distritos")
    public ResponseEntity<List<Distrito>> list(){
        List<Distrito> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Distrito Según el ID",httpMethod = "GET",nickname = "Detalle Distrito")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Distrito distrito = serv.getOne(id).get();
        return new ResponseEntity<>(distrito, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Distrito",httpMethod = "POST",nickname = "Registro Distrito")
    public ResponseEntity<?> create(@Valid @RequestBody Distrito distrito){
    	if(StringUtils.isBlank(distrito.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(distrito.getNombre().length()>50)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(distrito.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(distrito);
        return new ResponseEntity<>(new Mensaje("Distrito Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de la Clasificación",httpMethod = "PUT",nickname = "Actualizar Clasificación")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Distrito newdistrito){
    	if(StringUtils.isBlank(newdistrito.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newdistrito.getNombre().length()>50)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newdistrito.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Distrito distrito = serv.getOne(id).get();
        distrito.setNombre(newdistrito.getNombre());
        serv.save(distrito);
        return new ResponseEntity<>(new Mensaje("Distrito Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Distrito",httpMethod = "DELETE",nickname = "Eliminar Distrito")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Distrito Eliminado"), HttpStatus.OK);
    }

}

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
import pe.cine.model.Director;
import pe.cine.service.DirectorService;

@RestController
@RequestMapping("/director")
@CrossOrigin(origins = "http://localhost:4200")
public class DirectorRestController {

	@Autowired
	private DirectorService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Directores",httpMethod = "GET",nickname = "Listar Directores")
    public ResponseEntity<List<Director>> list(){
        List<Director> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Director Según el ID",httpMethod = "GET",nickname = "Detalle Director")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Director director = serv.getOne(id).get();
        return new ResponseEntity<>(director, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Director",httpMethod = "POST",nickname = "Registro Director")
    public ResponseEntity<?> create(@Valid @RequestBody Director director){
    	if(StringUtils.isBlank(director.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(director.getNombre().length()>40)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(director.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(director);
        return new ResponseEntity<>(new Mensaje("Director Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Director",httpMethod = "PUT",nickname = "Actualizar Director")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Director newdirector){
    	if(StringUtils.isBlank(newdirector.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newdirector.getNombre().length()>40)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newdirector.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Director director = serv.getOne(id).get();
        director.setNombre(newdirector.getNombre());
        serv.save(director);
        return new ResponseEntity<>(new Mensaje("Director Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Director",httpMethod = "DELETE",nickname = "Eliminación Director")
    public ResponseEntity<?> deleteF(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Director Eliminado"), HttpStatus.OK);
    }
}

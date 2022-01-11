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
import pe.cine.model.Genero;
import pe.cine.service.GeneroService;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneroRestController {

	@Autowired
	private GeneroService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Géneros",httpMethod = "GET",nickname = "Listar Géneros")
    public ResponseEntity<List<Genero>> list(){
        List<Genero> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Género Según el ID",httpMethod = "GET",nickname = "Detalle Género")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Genero genero = serv.getOne(id).get();
        return new ResponseEntity<>(genero, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Género",httpMethod = "POST",nickname = "Registro Género")
    public ResponseEntity<?> create(@Valid @RequestBody Genero genero){
    	if(StringUtils.isBlank(genero.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(genero.getNombre().length()>25)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(genero.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(genero);
        return new ResponseEntity<>(new Mensaje("Género Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Género",httpMethod = "PUT",nickname = "Actualizar Género")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Genero newgenero){
    	if(StringUtils.isBlank(newgenero.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newgenero.getNombre().length()>25)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newgenero.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Genero genero = serv.getOne(id).get();
        genero.setNombre(newgenero.getNombre());
        serv.save(genero);
        return new ResponseEntity<>(new Mensaje("Género Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Género",httpMethod = "DELETE",nickname = "Eliminar Género")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Género Eliminado"), HttpStatus.OK);
    }
}

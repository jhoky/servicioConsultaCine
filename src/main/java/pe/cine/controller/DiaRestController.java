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
import pe.cine.model.Dia;
import pe.cine.service.DiaService;

@RestController
@RequestMapping("/dia")
@CrossOrigin(origins = "http://localhost:4200")
public class DiaRestController {

	@Autowired
	private DiaService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Días",httpMethod = "GET",nickname = "Listar Día")
    public ResponseEntity<List<Dia>> list(){
        List<Dia> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Día Según el ID",httpMethod = "GET",nickname = "Detalle Día")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Dia dia = serv.getOne(id).get();
        return new ResponseEntity<>(dia, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Día",httpMethod = "POST",nickname = "Registro Día")
    public ResponseEntity<?> create(@Valid @RequestBody Dia dia){
    	if(StringUtils.isBlank(dia.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(dia.getNombre().length()>9)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(dia.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(dia);
        return new ResponseEntity<>(new Mensaje("Día Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Día",httpMethod = "PUT",nickname = "Actualizar Día")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Dia newdia){
    	if(StringUtils.isBlank(newdia.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newdia.getNombre().length()>9)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newdia.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Dia dia = serv.getOne(id).get();
        dia.setNombre(newdia.getNombre());
        serv.save(dia);
        return new ResponseEntity<>(new Mensaje("Día Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Día",httpMethod = "DELETE",nickname = "Eliminación Día")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Día Eliminado"), HttpStatus.OK);
    }
}

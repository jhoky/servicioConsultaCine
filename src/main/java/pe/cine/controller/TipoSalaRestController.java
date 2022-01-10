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
import pe.cine.model.Tipo_Sala;
import pe.cine.service.TipoSalaService;

@RestController
@RequestMapping("/tiposala")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoSalaRestController {

	@Autowired
	private TipoSalaService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Tipos de Sala",httpMethod = "GET",nickname = "Listar Tipos de Sala")
    public ResponseEntity<List<Tipo_Sala>> list(){
        List<Tipo_Sala> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de los Tipos de Sala Según el ID",httpMethod = "GET",nickname = "Detalle Tipo Sala")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Tipo_Sala tipo_sala = serv.getOne(id).get();
        return new ResponseEntity<>(tipo_sala, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Tipo de Sala",httpMethod = "POST",nickname = "Registro Tipo Sala")
    public ResponseEntity<?> create(@Valid @RequestBody Tipo_Sala tipo_sala){
    	if(StringUtils.isBlank(tipo_sala.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(tipo_sala.getNombre().length()>15)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(tipo_sala.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(tipo_sala);
        return new ResponseEntity<>(new Mensaje("Tipo Sala Creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de Tipo Sala",httpMethod = "PUT",nickname = "Actualizar Tipo Sala")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Tipo_Sala newtipo_sala){
    	if(StringUtils.isBlank(newtipo_sala.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newtipo_sala.getNombre().length()>15)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newtipo_sala.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Tipo_Sala tipo_sala = serv.getOne(id).get();
        tipo_sala.setNombre(newtipo_sala.getNombre());
        serv.save(tipo_sala);
        return new ResponseEntity<>(new Mensaje("Tipo Sala Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de Tipo Sala",httpMethod = "DELETE",nickname = "Eliminar Tipo Sala")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Tipo Sala Eliminada"), HttpStatus.OK);
    }
}

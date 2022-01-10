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
import pe.cine.model.Tipo_Publico;
import pe.cine.service.TipoPublicoService;

@RestController
@RequestMapping("/tipopublico")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoPublicoRestController {

	@Autowired
	private TipoPublicoService serv;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Tipos de Públicos",httpMethod = "GET",nickname = "Listar Tipos de Públicos")
    public ResponseEntity<List<Tipo_Publico>> list(){
        List<Tipo_Publico> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de los Tipos de Público Según el ID",httpMethod = "GET",nickname = "Detalle Tipo Público")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Tipo_Publico tipo_publico = serv.getOne(id).get();
        return new ResponseEntity<>(tipo_publico, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Tipo de Público",httpMethod = "POST",nickname = "Registro Tipo Público")
    public ResponseEntity<?> create(@Valid @RequestBody Tipo_Publico tipo_publico){
    	if(StringUtils.isBlank(tipo_publico.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(tipo_publico.getNombre().length()>40)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(tipo_publico.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        serv.save(tipo_publico);
        return new ResponseEntity<>(new Mensaje("Tipo Público Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de Tipo Público",httpMethod = "PUT",nickname = "Actualizar Tipo Público")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Tipo_Publico newtipo_publico){
    	if(StringUtils.isBlank(newtipo_publico.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newtipo_publico.getNombre().length()>40)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(newtipo_publico.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
        Tipo_Publico tipo_publico = serv.getOne(id).get();
        tipo_publico.setNombre(newtipo_publico.getNombre());
        serv.save(tipo_publico);
        return new ResponseEntity<>(new Mensaje("Tipo Público Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de Tipo Público",httpMethod = "DELETE",nickname = "Eliminar Tipo Público")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Tipo Público Eliminado"), HttpStatus.OK);
    }

}

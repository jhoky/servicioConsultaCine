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
import pe.cine.model.Horario;
import pe.cine.service.HorarioService;

@RestController
@RequestMapping("/horario")
@CrossOrigin(origins = "http://localhost:4200")
public class HorarioRestController {
	
	@Autowired
	HorarioService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Horarios",httpMethod = "GET",nickname = "Listar Horarios")
    public ResponseEntity<List<Horario>> list(){
        List<Horario> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{cine}/{pelicula}/{fech}")
	@ApiOperation(value = "Realiza un Listado de Todos los Horarios Según los Filtros",httpMethod = "GET",nickname = "Listar Horarios Por Filtros")
    public ResponseEntity<List<Horario>> listF(@PathVariable("cine") int cine, @PathVariable("pelicula") int pelicula, @PathVariable("fech") String fech){
        List<Horario> list = serv.listarFiltros(cine, pelicula, fech);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Horario Según el ID",httpMethod = "GET",nickname = "Detalle Horario")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Horario horario = serv.getOne(id).get();
        return new ResponseEntity<>(horario, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Horario",httpMethod = "POST",nickname = "Registro Horario")
    public ResponseEntity<?> create(@Valid @RequestBody Horario horario){
	    if(!serv.listarHoras(horario.getCine().getCine_id(), horario.getPelicula().getPelicula_id(), horario.getFecha(), horario.getHora_inicio()).isEmpty()) {
    		return new ResponseEntity<>(new Mensaje("Ya Existe Ese Registro"), HttpStatus.BAD_REQUEST);
    	}
    	horario.setEstado(true);
        serv.save(horario);
        return new ResponseEntity<>(new Mensaje("Horario Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Horario",httpMethod = "PUT",nickname = "Actualizar Horario")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Horario newhorario){
	    if(!serv.listarHoras(newhorario.getCine().getCine_id(), newhorario.getPelicula().getPelicula_id(), newhorario.getFecha(), newhorario.getHora_inicio()).isEmpty()) {
    		return new ResponseEntity<>(new Mensaje("Ya Existe Ese Registro"), HttpStatus.BAD_REQUEST);
    	}
        Horario horario = serv.getOne(id).get();
        horario.setFecha(newhorario.getFecha());
        horario.setHora_inicio(newhorario.getHora_inicio());
        horario.setCine(newhorario.getCine());
        horario.setPelicula(newhorario.getPelicula());
        horario.setEstado(horario.isEstado());
        serv.save(horario);
        return new ResponseEntity<>(new Mensaje("Horario Actualizado"), HttpStatus.OK);
    }
    
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "Realiza una Eliminación Lógica del Horario",httpMethod = "PUT",nickname = "Eliminar Horario")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        Horario horario = serv.getOne(id).get();
        if(horario.isEstado() == true) {
        	horario.setEstado(false);
        }else {
        	horario.setEstado(true);
        }
        serv.save(horario);
        return new ResponseEntity<>(new Mensaje("Estado Cambiado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Horario",httpMethod = "DELETE",nickname = "Eliminación Horario")
    public ResponseEntity<?> deleteF(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Horario Eliminado"), HttpStatus.OK);
    }
}

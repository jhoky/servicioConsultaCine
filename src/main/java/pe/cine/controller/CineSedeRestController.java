package pe.cine.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.CineSede;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Cine;
import pe.cine.model.Sede;
import pe.cine.service.CineService;
import pe.cine.service.SedeService;

@RestController
@RequestMapping("/cinesede")
@CrossOrigin(origins = "http://localhost:4200")
public class CineSedeRestController {

	@Autowired
	private CineService cineService;

	@Autowired
	private SedeService sedeService;

	@GetMapping(value = "/listar/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Realiza un Listado de Todas las Sedes Pertenecientes a una Cadena de Cine", httpMethod = "GET", nickname = "Lista de Sedes de un Cine")
	public ResponseEntity<?> listar(@PathVariable("id") int id)
	{
		Cine cineDb = cineService.getOne(id).get();
		if(cineDb != null) 
		{
			Set<Sede> listasedes = cineDb.getLista_sedes();
			return new ResponseEntity<>(listasedes, HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cine no existe");
	}

	@GetMapping(value = "/listar/{id}")
	@ApiOperation(value = "Realiza un Listado de Todas las Sedes Pertenecientes a una Cadena de Cine", httpMethod = "GET", nickname = "Lista de Sedes de un Cine")
	public ResponseEntity<?> listarcinesede(@PathVariable("id") int id) {

		if (!cineService.existsById(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cine no existe");
		
		Cine cine = cineService.getOne(id).get();
		Set<Sede> listasedes = cine.getLista_sedes();
		return new ResponseEntity<>(listasedes, HttpStatus.OK);

	}

	@PostMapping(value = "/agregar", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Asocia las Sedes a un Cine", httpMethod = "POST", nickname = "Asociar Sedes con Cine")
	public ResponseEntity<?> agregar(@RequestBody CineSede cine_sede) {

		Cine cineDb = cineService.getOne(cine_sede.getCine().getCine_id()).get();
		if (cineDb != null) {
			Sede sedeDb = sedeService.getOne(cine_sede.getSede().getSede_id()).get();
			if (sedeDb != null) {
				cineDb.getLista_sedes().add(sedeDb);
				cineService.save(cineDb);
				return new ResponseEntity<>("Sede asociada a Cine", HttpStatus.CREATED);
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sede no existe");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cine no existe");
	}
	
	@DeleteMapping("/delete/{cine}/{sede}")
    @ApiOperation(value = "Eliminar Vinculación de Cine y Sede",httpMethod = "DELETE",nickname = "Eliminar Cine Sede")
    public ResponseEntity<?> delete(@PathVariable("cine")int cine, @PathVariable("sede")int sede){
        cineService.deleteCinSed(cine, sede);
        return new ResponseEntity<>(new Mensaje("Cine Y Sede Desvinculados"), HttpStatus.OK);
    }

}

package pe.cine.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.CinePelicula;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Cine;
import pe.cine.model.Pelicula;
import pe.cine.service.CineService;
import pe.cine.service.PeliculaService;

@RestController
@RequestMapping("/cinepelicula")
@CrossOrigin(origins = "http://localhost:4200")
public class CinePeliculaRestController {

	@Autowired
	private CineService cineService;

	@Autowired
	private PeliculaService peliculaService;

	@GetMapping(value = "/listar/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Realiza un Listado de Todas las Peliculas Pertenecientes a un Cine", httpMethod = "GET", nickname = "Lista de Peliculas de un Cine")
	public ResponseEntity<?> listar(@PathVariable("id") int id)
	{
		Cine cineDb = cineService.getOne(id).get();
		if(cineDb != null) 
		{
			Set<Pelicula> listapeliculas = cineDb.getLista_peliculas();
			return new ResponseEntity<>(listapeliculas, HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cine no existe");
	}

	@GetMapping(value = "/listarca/{id}")
	@ApiOperation(value = "Realiza un Listado de Todas las Peliculas Pertenecientes a un Cine", httpMethod = "GET", nickname = "Lista de Peliculas de un Cine")
	public ResponseEntity<?> listarcp(@PathVariable("id") int id) {
		if (!cineService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cine no existe");			
		}
		Cine cineDB = cineService.getOne(id).get();
		System.out.println(cineDB);
		Set<Pelicula> listapeliculas = cineDB.getLista_peliculas();
		return new ResponseEntity<>(listapeliculas, HttpStatus.OK);
		
	}

	@PostMapping(value = "/agregar", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Asocia las Peliculas a un Cine", httpMethod = "POST", nickname = "Asociar Peliculas con Cine")
	public ResponseEntity<?> agregar(@RequestBody CinePelicula cine_pelicula) {

		Cine cineDb = cineService.getOne(cine_pelicula.getCine().getCine_id()).get();
		if (cineDb != null) {
			Pelicula peliculaDb = peliculaService.getOne(cine_pelicula.getPelicula().getPelicula_id()).get();
			if (peliculaDb != null) {
				cineDb.getLista_peliculas().add(peliculaDb);
				cineService.save(cineDb);
				return new ResponseEntity<>("Pelicula asociada a Cine", HttpStatus.CREATED);
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pelicula no existe");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cine no existe");
	}
	
	@DeleteMapping("/delete/{cine}/{pelicula}")
    @ApiOperation(value = "Eliminar Vinculación de Cine y Película",httpMethod = "DELETE",nickname = "Eliminar Cine Película")
    public ResponseEntity<?> delete(@PathVariable("cine")int cine, @PathVariable("pelicula")int pelicula){
        cineService.deleteCinPel(cine, pelicula);
        return new ResponseEntity<>(new Mensaje("Cine Y Película Desvinculados"), HttpStatus.OK);
    }

}

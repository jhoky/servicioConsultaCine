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
import pe.cine.dto.PeliculaGenero;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Pelicula;
import pe.cine.model.Genero;
import pe.cine.service.GeneroService;
import pe.cine.service.PeliculaService;

@RestController
@RequestMapping("/peliculagenero")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaGeneroRestController {

	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping(value = "/listar/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Realiza un Listado de Todos los Generos que tiene una Pelicula", httpMethod = "GET", nickname = "Lista de Generos de una Pelicula")
	public ResponseEntity<?> listar(@PathVariable("id") int id)
	{
		Pelicula peliculaDb = peliculaService.getOne(id).get();
		if(peliculaDb != null) 
		{
			Set<Genero> listageneros = peliculaDb.getLista_genero();
			return new ResponseEntity<>(listageneros, HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pelicula no existe");
	}
	
	@PostMapping(value = "/agregar", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
									consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Asocia los Generos a una Pelicula", httpMethod = "POST", nickname = "Asociar Generos con Pelicula")
	public ResponseEntity<?> agregar(@RequestBody PeliculaGenero pelicula_genero)
	{
		
		Pelicula peliculaDb = peliculaService.getOne(pelicula_genero.getPelicula().getPelicula_id()).get();
		if(peliculaDb !=null) 
		{
			Genero generoDb = generoService.getOne(pelicula_genero.getGenero().getGenero_id()).get();
			if(generoDb != null)
			{
				peliculaDb.getLista_genero().add(generoDb);
				peliculaService.save(peliculaDb); 
				return new ResponseEntity<>("Genero asociado a Pelicula", HttpStatus.CREATED);
			}
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Genero no existe");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pelicula no existe");
	}
	
	@DeleteMapping("/delete/{pelicula}/{genero}")
    @ApiOperation(value = "Eliminar Vinculación de Película y Género",httpMethod = "DELETE",nickname = "Eliminar Película Género")
    public ResponseEntity<?> delete(@PathVariable("pelicula")int pelicula, @PathVariable("genero")int genero){
        peliculaService.deletePelGen(pelicula, genero);
        return new ResponseEntity<>(new Mensaje("Película Y Género Desvinculados"), HttpStatus.OK);
    }

}

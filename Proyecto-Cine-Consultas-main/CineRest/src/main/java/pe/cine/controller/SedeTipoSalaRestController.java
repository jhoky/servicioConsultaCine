package pe.cine.controller;

import java.util.ArrayList;
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
import pe.cine.dto.SedeTipoSala;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Sede;
import pe.cine.model.Tipo_Sala;
import pe.cine.service.SedeService;
import pe.cine.service.TipoSalaService;

@RestController
@RequestMapping("/sedetiposala")
@CrossOrigin(origins = "http://localhost:4200")
public class SedeTipoSalaRestController {

	@Autowired
	private SedeService sedeService;
	
	@Autowired
	private TipoSalaService tiposalaService;
	
	@GetMapping(value = "/listar/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Realiza un Listado de Todos los Tipos de Sala que hay en una Sede", httpMethod = "GET", nickname = "Lista de Tipos de Sala en una Sede")
	public ResponseEntity<?> listar(@PathVariable("id") int id)
	{
		Sede sedeDb = sedeService.getOne(id).get();
		if(sedeDb != null) 
		{
			Set<Tipo_Sala> listatiposalas = sedeDb.getTipos_sala();
			return new ResponseEntity<>(listatiposalas, HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sede no existe");
	}
	
	@PostMapping(value = "/agregar", produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
									consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Asocia los Tipos de Sala a una Sede", httpMethod = "POST", nickname = "Asociar Tipos de Sala con Sede")
	public ResponseEntity<?> agregar(@RequestBody SedeTipoSala sede_tiposala)
	{
		
		Sede sedeDb = sedeService.getOne(sede_tiposala.getSede().getSede_id()).get();
		if(sedeDb !=null) 
		{
			Tipo_Sala tiposalaDb = tiposalaService.getOne(sede_tiposala.getTipo_sala().getTipo_sala_id()).get();
			if(tiposalaDb != null)
			{
				sedeDb.getTipos_sala().add(tiposalaDb);
				sedeService.save(sedeDb); 
				return new ResponseEntity<>("Tipo de Sala asociado a Sede", HttpStatus.CREATED);
			}
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Tipo de Sala no existe");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sede no existe");
	}
	
	@DeleteMapping("/delete/{sede}/{sala}")
    @ApiOperation(value = "Eliminar Vinculación de Sede y Sala",httpMethod = "DELETE",nickname = "Eliminar Sede Sala")
    public ResponseEntity<?> delete(@PathVariable("sede")int sede, @PathVariable("sala")int sala){
        sedeService.deleteSedSal(sede, sala);
        return new ResponseEntity<>(new Mensaje("Sede Y Sala Desvinculados"), HttpStatus.OK);
    }
	
	
	@GetMapping("/obtenertiposala/{id}")
    @ApiOperation(value = "Obtener los tiposala",httpMethod = "GET",nickname = "Obtener Id")
    public ResponseEntity<?> getId(@PathVariable("id") int idsede){
		ArrayList<String> listatiposala = sedeService.buscartiposala(idsede);
        return new ResponseEntity<>(listatiposala, HttpStatus.OK);
    }

}

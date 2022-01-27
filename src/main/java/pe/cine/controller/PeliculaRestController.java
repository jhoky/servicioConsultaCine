package pe.cine.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.security.Mensaje;
import pe.cine.model.Horario;
import pe.cine.model.Pelicula;
import pe.cine.service.CloudinaryService;
import pe.cine.service.PeliculaService;

@RestController
@RequestMapping("/pelicula")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaRestController {
	
	@Autowired
	private PeliculaService serv;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@GetMapping("/listaActivos")
	@ApiOperation(value = "Realiza un Listado de Todas las Películas Activas",httpMethod = "GET",nickname = "Listar Películas Activas")
    public ResponseEntity<List<Pelicula>> listActivos(){
        List<Pelicula> list = serv.listActivos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/listaDesc")
	@ApiOperation(value = "Realiza un Listado Descendente de Todas las Películas",httpMethod = "GET",nickname = "Lista Descendente Películas")
    public ResponseEntity<List<Pelicula>> listDesc(){
        List<Pelicula> list = serv.listDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todas las Películas",httpMethod = "GET",nickname = "Listar Películas")
    public ResponseEntity<List<Pelicula>> list(){
        List<Pelicula> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/obtenerId")
    @ApiOperation(value = "Obtener el Último ID Registrado de Película",httpMethod = "GET",nickname = "Obtener Id")
    public ResponseEntity<?> getId(){
		int id = serv.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de la Película Según el ID",httpMethod = "GET",nickname = "Detalle Película")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Pelicula pelicula = serv.getOne(id).get();
        return new ResponseEntity<>(pelicula, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro de la Película",httpMethod = "POST",nickname = "Registro Película")
    public ResponseEntity<?> create(@Valid @RequestBody Pelicula pelicula){
    	if(StringUtils.isBlank(pelicula.getNombre()))
            return new ResponseEntity(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(pelicula.getNombre().length()>80)
    		return new ResponseEntity(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(pelicula.getDescripcion()))
            return new ResponseEntity(new Mensaje("La Descripción No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(pelicula.getDescripcion().length()>800)
    		return new ResponseEntity(new Mensaje("La Descripción Es Demasiado Larga"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(pelicula.getDuracion()))
            return new ResponseEntity(new Mensaje("La Duración No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(pelicula.getDuracion().length()>10)
    		return new ResponseEntity(new Mensaje("La Duración Es Demasiado Larga"), HttpStatus.BAD_REQUEST);
    	
    	String x = pelicula.getDuracion();
    	x = x.substring(0, 2) + "h" + x.substring(2, x.length());  
    	x = x.replace(":", " ");
    	pelicula.setDuracion(x+"m");
    	pelicula.setEstado(true);
        serv.save(pelicula);
        return new ResponseEntity<>(new Mensaje("Película Creada"), HttpStatus.OK);
    }
    
    @PostMapping("/createimagen/{id}")
    @ApiOperation(value = "Realiza el Registro de la Imagen de la Película",httpMethod = "POST",nickname = "Registro Imagen")
    public ResponseEntity<?> createImagen(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("Imagen No Válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Pelicula pelicula = serv.getOne(id).get();
        pelicula.setImagenUrl((String)result.get("url"));
        serv.save(pelicula);
        return new ResponseEntity(new Mensaje("Imagen Subida"), HttpStatus.OK);
    }
	
	@PostMapping("/validarimg")
    @ApiOperation(value = "Valida si el Archivo es una Imagen",httpMethod = "POST",nickname = "Validar Imagen")
    public Boolean validarImagen(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return false;
        }
        return true;
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de la Película",httpMethod = "PUT",nickname = "Actualizar Película")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Pelicula newpelicula){
    	if(StringUtils.isBlank(newpelicula.getNombre()))
            return new ResponseEntity(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newpelicula.getNombre().length()>80)
    		return new ResponseEntity(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(newpelicula.getDescripcion()))
            return new ResponseEntity(new Mensaje("La Descripción No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(newpelicula.getDescripcion().length()>800)
    		return new ResponseEntity(new Mensaje("La Descripción Es Demasiado Larga"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(newpelicula.getDuracion()))
            return new ResponseEntity(new Mensaje("La Duración No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(newpelicula.getDuracion().length()>10)
    		return new ResponseEntity(new Mensaje("La Duración Es Demasiado Larga"), HttpStatus.BAD_REQUEST);
        Pelicula pelicula = serv.getOne(id).get();
        pelicula.setNombre(newpelicula.getNombre());
        pelicula.setDescripcion(newpelicula.getDescripcion());
        String x = newpelicula.getDuracion();
    	x = x.substring(0, 2) + "h" + x.substring(2, x.length());  
    	x = x.replace(":", " ");
    	pelicula.setDuracion(x+"m");
        pelicula.setFecha_estreno(newpelicula.getFecha_estreno());
        pelicula.setDirector(newpelicula.getDirector());
        pelicula.setClasificacion(newpelicula.getClasificacion());
        pelicula.setEstado(newpelicula.isEstado());
        serv.save(pelicula);
        return new ResponseEntity<>(new Mensaje("Película Actualizada"), HttpStatus.OK);
    }
    
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "Realiza una Eliminación Lógica de la Película",httpMethod = "PUT",nickname = "Eliminar Película")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        Pelicula pelicula = serv.getOne(id).get();
        if(pelicula.isEstado() == true) {
        	pelicula.setEstado(false);
        }else {
        	pelicula.setEstado(true);
        }
        serv.save(pelicula);
        return new ResponseEntity<>(new Mensaje("Estado Cambiado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de la Película",httpMethod = "DELETE",nickname = "Eliminación Película")
    public ResponseEntity<?> deleteF(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity(new Mensaje("Película Eliminada"), HttpStatus.OK);
    }
    
    
    @GetMapping("/listacartelera")
	@ApiOperation(value = "Realiza un Listado de las películas en la semana",httpMethod = "GET",nickname = "Listar Cartelera")
    public ResponseEntity<List<Pelicula>> listaCartelera(){
        List<Pelicula> list = serv.listarCartelera();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/horariospelicula/{id}/{idcine}")
    @ApiOperation(value = "lista de horarios segun pelicula",httpMethod = "GET",nickname = "horarios Película")
    public ResponseEntity<ArrayList<Horario>> listaHorariosporPelicula(@PathVariable("id") int id, @PathVariable("idcine") int idcine){
        if(!serv.existsById(id))
            return new ResponseEntity(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        ArrayList<Horario> lhorario = serv.listahorariospelicula(id, idcine);
        return new ResponseEntity<>(lhorario, HttpStatus.OK);
    }
    
    

}


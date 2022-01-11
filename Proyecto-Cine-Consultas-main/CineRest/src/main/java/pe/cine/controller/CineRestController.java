package pe.cine.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
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
import pe.cine.model.Cine;
import pe.cine.service.CineService;
import pe.cine.service.CloudinaryService;

@RestController
@RequestMapping("/cine")
@CrossOrigin(origins = "http://localhost:4200")
public class CineRestController {
	
	@Autowired
	private CineService serv;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Cines",httpMethod = "GET",nickname = "Listar Cines")
    public ResponseEntity<List<Cine>> list(){
        List<Cine> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/obtenerId")
    @ApiOperation(value = "Obtener el Último ID Registrado de Película",httpMethod = "GET",nickname = "Obtener Id")
    public ResponseEntity<?> getId(){
		int id = serv.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Cine Según el ID",httpMethod = "GET",nickname = "Detalle Cine")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Cine cine = serv.getOne(id).get();
        return new ResponseEntity<>(cine, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Cine",httpMethod = "POST",nickname = "Registro Cine")
    public ResponseEntity<?> create(@Valid @RequestBody Cine cine){
    	if(StringUtils.isBlank(cine.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(cine.getNombre().length()>20)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(cine.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
    	cine.setEstado(true);
        serv.save(cine);
        return new ResponseEntity<>(new Mensaje("Cine Creado"), HttpStatus.OK);
    }
	
	 @PostMapping("/createimagen/{id}")
    @ApiOperation(value = "Realiza el Registro de la Imagen del Cine",httpMethod = "POST",nickname = "Registro Imagen")
    public ResponseEntity<?> createImagen(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity<>(new Mensaje("Imagen No Válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Cine cine = serv.getOne(id).get();
        cine.setImagen_url_cine((String)result.get("url"));
        serv.save(cine);
        return new ResponseEntity<>(new Mensaje("Imagen Subida"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Cine",httpMethod = "PUT",nickname = "Actualizar Cine")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Cine newcine){
    	if(StringUtils.isBlank(newcine.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newcine.getNombre().length()>20)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
        Cine cine = serv.getOne(id).get();
        cine.setNombre(newcine.getNombre());
        cine.setEstado(newcine.isEstado());
        serv.save(cine);
        return new ResponseEntity<>(new Mensaje("Cine Actualizado"), HttpStatus.OK);
    }
    
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "Realiza una Eliminación Lógica del Cine",httpMethod = "PUT",nickname = "Eliminar Cine")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        Cine cine = serv.getOne(id).get();
        if(cine.isEstado() == true) {
        	cine.setEstado(false);
        }else {
        	cine.setEstado(true);
        }
        serv.save(cine);
        return new ResponseEntity<>(new Mensaje("Estado Cambiado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Cine",httpMethod = "DELETE",nickname = "Eliminación Cine")
    public ResponseEntity<?> deleteF(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Cine Eliminado"), HttpStatus.OK);
    }
	
}

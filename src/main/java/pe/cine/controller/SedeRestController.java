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
import pe.cine.model.Sede;
import pe.cine.service.CloudinaryService;
import pe.cine.service.SedeService;

@RestController
@RequestMapping("/sede")
@CrossOrigin
public class SedeRestController {

	@Autowired
	private SedeService serv;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todas las Sedes",httpMethod = "GET",nickname = "Listar Sedes")
    public ResponseEntity<List<Sede>> list(){
        List<Sede> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/obtenerId")
    @ApiOperation(value = "Obtener el Último ID Registrado de Sede",httpMethod = "GET",nickname = "Obtener Id")
    public ResponseEntity<?> getId(){
		int id = serv.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle de la Sede Según el ID",httpMethod = "GET",nickname = "Detalle Sede")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id)) {
        	return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        }
            
        Sede sede = serv.getOne(id).get();
        return new ResponseEntity<>(sede, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro de la Sede",httpMethod = "POST",nickname = "Registro Sede")
    public ResponseEntity<?> create(@Valid @RequestBody Sede sede){
    	if(StringUtils.isBlank(sede.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(sede.getNombre().length()>50)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(serv.existsByNombre(sede.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Nombre Ya Existe"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(sede.getDireccion()))
            return new ResponseEntity<>(new Mensaje("La Dirección No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(sede.getDireccion().length()>300)
    		return new ResponseEntity<>(new Mensaje("La Dirección Es Demasiada Larga"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(sede.getUbicacion()))
            return new ResponseEntity<>(new Mensaje("La Ubicación No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(sede.getUbicacion().length()>150)
    		return new ResponseEntity<>(new Mensaje("La Ubicación Es Demasiada Larga"), HttpStatus.BAD_REQUEST);
    	if(sede.getCantidad_salas()<=0)
    		return new ResponseEntity<>(new Mensaje("La Cantidad De Salas Debe Ser Mayor A 0"), HttpStatus.BAD_REQUEST);
    	if(!sede.getUbicacion().contains(","))
    		return new ResponseEntity<>(new Mensaje("Coordenadas Incorrectas"), HttpStatus.BAD_REQUEST);
    	sede.setEstado(true);
        serv.save(sede);
        return new ResponseEntity<>(new Mensaje("Sede Creada"), HttpStatus.OK);
    }
    
    @PostMapping("/createimagen/{id}")
    @ApiOperation(value = "Realiza el Registro de la Imagen de la Sede",httpMethod = "POST",nickname = "Registro Imagen")
    public ResponseEntity<?> createImagen(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity<>(new Mensaje("Imagen No Válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Sede sede = serv.getOne(id).get();
        sede.setImagenUrl((String)result.get("url"));
        serv.save(sede);
        return new ResponseEntity<>(new Mensaje("Imagen Subida"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos de la Sede",httpMethod = "PUT",nickname = "Actualizar Sede")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Sede newsede){
    	if(StringUtils.isBlank(newsede.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
    	if(newsede.getNombre().length()>50)
    		return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(newsede.getDireccion()))
            return new ResponseEntity<>(new Mensaje("La Dirección No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(newsede.getDireccion().length()>300)
    		return new ResponseEntity<>(new Mensaje("La Dirección Es Demasiada Larga"), HttpStatus.BAD_REQUEST);
    	if(StringUtils.isBlank(newsede.getUbicacion()))
            return new ResponseEntity<>(new Mensaje("La Ubicación No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
    	if(newsede.getUbicacion().length()>150)
    		return new ResponseEntity<>(new Mensaje("La Ubicación Es Demasiada Larga"), HttpStatus.BAD_REQUEST);
    	if(newsede.getCantidad_salas()<=0)
    		return new ResponseEntity<>(new Mensaje("La Cantidad De Salas Debe Ser Mayor A 0"), HttpStatus.BAD_REQUEST);
    	if(!sede.getUbicacion().contains(","))
    		return new ResponseEntity<>(new Mensaje("Coordenadas Incorrectas"), HttpStatus.BAD_REQUEST);
        Sede sede = serv.getOne(id).get();
        sede.setNombre(newsede.getNombre());
        sede.setDireccion(newsede.getDireccion());
        sede.setUbicacion(newsede.getUbicacion());
        sede.setCantidad_salas(newsede.getCantidad_salas());
        sede.setDistrito(newsede.getDistrito());
        sede.setEstado(sede.isEstado());
        serv.save(sede);
        return new ResponseEntity<>(new Mensaje("Sede Actualizada"), HttpStatus.OK);
    }
    
    @PutMapping("/delete/{id}")
    @ApiOperation(value = "Realiza una Eliminación Lógica de la Sede",httpMethod = "PUT",nickname = "Eliminar Sede")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        Sede sede = serv.getOne(id).get();
        if(sede.isEstado() == true) {
        	sede.setEstado(false);
        }else {
        	sede.setEstado(true);
        }
        serv.save(sede);
        return new ResponseEntity<>(new Mensaje("Estado Cambiado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física de la Sede",httpMethod = "DELETE",nickname = "Eliminación Sede")
    public ResponseEntity<?> deleteF(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Sede Eliminada"), HttpStatus.OK);
    }

}

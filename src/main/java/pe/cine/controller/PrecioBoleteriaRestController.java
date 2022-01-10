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
import pe.cine.model.Precio_Boleteria;
import pe.cine.service.PrecioBoleteriaService;

@RestController
@RequestMapping("/precioboleteria")
@CrossOrigin(origins = "http://localhost:4200")
public class PrecioBoleteriaRestController {

	@Autowired
	private PrecioBoleteriaService serv;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Precios",httpMethod = "GET",nickname = "Listar Precios")
    public ResponseEntity<List<Precio_Boleteria>> list(){
        List<Precio_Boleteria> list = serv.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	@GetMapping("/lista/{cine}/{dia}/{sala}")
	@ApiOperation(value = "Realiza un Listado de Todos los Precios Según los Filtros",httpMethod = "GET",nickname = "Listar Precios Por Filtros")
    public ResponseEntity<List<Precio_Boleteria>> listF(@PathVariable("cine") int cine, @PathVariable("dia") int dia, @PathVariable("sala") int sala){
        List<Precio_Boleteria> list = serv.listarFiltros(cine, dia, sala);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Detalle del Precio Según el ID",httpMethod = "GET",nickname = "Detalle Precio")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        Precio_Boleteria precio_boleteria = serv.getOne(id).get();
        return new ResponseEntity<>(precio_boleteria, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "Realiza el Registro del Precio",httpMethod = "POST",nickname = "Registro Precio")
    public ResponseEntity<?> create(@Valid @RequestBody Precio_Boleteria precio_boleteria){
    	if(precio_boleteria.getPrecio()<=0)
    		return new ResponseEntity<>(new Mensaje("El Precio Debe Ser Mayor A 0"), HttpStatus.BAD_REQUEST);
        serv.save(precio_boleteria);
        return new ResponseEntity<>(new Mensaje("Precio Creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Actualiza los Campos del Precio",httpMethod = "PUT",nickname = "Actualizar Precio")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Precio_Boleteria newprecio_boleteria){
    	if(newprecio_boleteria.getPrecio()<=0)
    		return new ResponseEntity<>(new Mensaje("El Precio Debe Ser Mayor A 0"), HttpStatus.BAD_REQUEST);
        Precio_Boleteria precio_boleteria = serv.getOne(id).get();
        precio_boleteria.setPrecio(newprecio_boleteria.getPrecio());
        precio_boleteria.setDia(newprecio_boleteria.getDia());
        precio_boleteria.setTipo_publico(newprecio_boleteria.getTipo_publico());
        precio_boleteria.setTipo_sala(newprecio_boleteria.getTipo_sala());
        precio_boleteria.setCine(newprecio_boleteria.getCine());
        serv.save(precio_boleteria);
        return new ResponseEntity<>(new Mensaje("Precio Actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Eliminación Física del Precio",httpMethod = "DELETE",nickname = "Eliminar Precio")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        serv.delete(id);
        return new ResponseEntity<>(new Mensaje("Precio Eliminado"), HttpStatus.OK);
    }
    
    
    @GetMapping("/detailforcine/{cine_id}")
    @ApiOperation(value = "Detalle del Precio Según el ID",httpMethod = "GET",nickname = "Detalle Precio")
    public ResponseEntity<?> buscarPorCine(@PathVariable("cine_id") int id){
        if(!serv.existsById(id))
            return new ResponseEntity<>(new Mensaje("No Existe"), HttpStatus.NOT_FOUND);
        List<Precio_Boleteria> precio_boleteria = serv.buscarprecioporcine(id);
        return new ResponseEntity<>(precio_boleteria, HttpStatus.OK);
    }

    
    
}

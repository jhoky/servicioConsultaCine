package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Clasificacion;
import pe.cine.service.ClasificacionService;

@Component
@Order(2)
public class CreateClasificacion implements CommandLineRunner{
	
	@Autowired
	ClasificacionService serv;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("B14") && !serv.existsByNombre("A")) {
	    	Clasificacion clasA = new Clasificacion("A"); Clasificacion clasB = new Clasificacion("B");
	    	Clasificacion clasB14 = new Clasificacion("B14"); Clasificacion clasC = new Clasificacion("C");
	        serv.save(clasA); serv.save(clasB); serv.save(clasB14); serv.save(clasC);
	    }else {
	    		System.out.println("Ya se han creado las clasificaciones");
	    }
	}

}

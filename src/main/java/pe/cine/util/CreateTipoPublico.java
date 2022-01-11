package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Tipo_Publico;
import pe.cine.service.TipoPublicoService;

@Component
@Order(6)
public class CreateTipoPublico implements CommandLineRunner{
	
	@Autowired
	TipoPublicoService serv;

	@Override
	public void run(String... args) throws Exception {

		if(!serv.existsByNombre("General") && !serv.existsByNombre("Niños y Adultos Mayores")) {
	    	Tipo_Publico tp1 = new Tipo_Publico("General");
	    	Tipo_Publico tp2 = new Tipo_Publico("Niños y Adultos Mayores");
	        serv.save(tp1); serv.save(tp2);
	    }else {
	    		System.out.println("Ya se han creado los tipos de públicos");
	    }
		
	}

}

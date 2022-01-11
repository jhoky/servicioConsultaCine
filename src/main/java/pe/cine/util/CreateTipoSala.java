package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Tipo_Sala;
import pe.cine.service.TipoSalaService;

@Component
@Order(7)
public class CreateTipoSala implements CommandLineRunner{
	
	@Autowired
	TipoSalaService serv;

	@Override
	public void run(String... args) throws Exception {
		
		if(!serv.existsByNombre("2D") && !serv.existsByNombre("3D")) {
	    	Tipo_Sala ts1 = new Tipo_Sala("2D");
	    	Tipo_Sala ts2 = new Tipo_Sala("3D");
	    	Tipo_Sala ts3 = new Tipo_Sala("4D");
	        serv.save(ts1); serv.save(ts2); serv.save(ts3);
	    }else {
	    		System.out.println("Ya se han creado los tipos de salas");
	    }
		
	}

}

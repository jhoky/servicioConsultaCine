
package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Director;
import pe.cine.service.DirectorService;

@Component
@Order(10)
public class CreateDirectores implements CommandLineRunner{
	
	@Autowired
	DirectorService serv;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Martin Scorsese") && !serv.existsByNombre("Jon Watts")) {
	    	Director d1 = new Director("Martin Scorsese");
	    	Director d2 = new Director("Pedro Almodóvar");
	    	Director d3 = new Director("Quentin Tarantino");
	    	Director d4 = new Director("Steven Spielberg");
	    	Director d5 = new Director("Jon Watts");
	    	Director d6 = new Director("David Fincher");
	    	Director d7 = new Director("Alejandro González Iñárritu");
	    	Director d8 = new Director("Tim Burton");
	    	Director d9 = new Director("Christopher Nolan");
	    	Director d10 = new Director("Guillermo Del Toro");
	    	Director d11 = new Director("Lana Wachowski");
	    	Director d12 = new Director("Ridley Scott");
	    	serv.save(d1);serv.save(d2);serv.save(d3);serv.save(d4);serv.save(d5);
	    	serv.save(d6);serv.save(d7);serv.save(d8);serv.save(d9);serv.save(d10);
	    	serv.save(d11);serv.save(d12);
	    }else {
	    		System.out.println("Ya se han creado los directores");
	    }
	}

}

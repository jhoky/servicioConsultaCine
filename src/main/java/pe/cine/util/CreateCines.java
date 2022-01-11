package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Cine;
import pe.cine.service.CineService;

@Component
@Order(3)
public class CreateCines implements CommandLineRunner{
	
	@Autowired
	CineService serv;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Cineplanet") && !serv.existsByNombre("CineStar")) {
	    	Cine cinePl = new Cine("Cineplanet",true,"https://res.cloudinary.com/drmoiteba/image/upload/v1641709257/cineplanet_nh90kq.jpg"); 
	    	Cine cineSt = new Cine("CineStar",true,"https://res.cloudinary.com/drmoiteba/image/upload/v1641709257/cinestar_hqvewr.jpg");
	    	Cine cineMa = new Cine("Cinemark",true,"https://res.cloudinary.com/drmoiteba/image/upload/v1641709257/cinemark_b13clt.jpg"); 
	    	Cine cinePo = new Cine("Cinépolis",true,"https://res.cloudinary.com/drmoiteba/image/upload/v1641709257/cinepolis_z41ilc.jpg");
	        serv.save(cinePl); serv.save(cineSt); serv.save(cineMa); serv.save(cinePo);
	    }else {
	    		System.out.println("Ya se han creado los cines");
	    }
	}

}

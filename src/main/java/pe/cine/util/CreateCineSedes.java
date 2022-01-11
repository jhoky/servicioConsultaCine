package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.service.CineService;

@Component
@Order(15)
public class CreateCineSedes implements CommandLineRunner{

	@Autowired
	CineService cineService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(cineService.listarCinSed().isEmpty()) {
			
			//CINEPLANET
			cineService.insertCinSed(1, 3);
			cineService.insertCinSed(1, 4);
			cineService.insertCinSed(1, 5);
			cineService.insertCinSed(1, 6);
			cineService.insertCinSed(1, 7);
			
			//CINESTAR
			cineService.insertCinSed(2, 8);
			cineService.insertCinSed(2, 9);
			cineService.insertCinSed(2, 10);
			cineService.insertCinSed(2, 11);
			cineService.insertCinSed(2, 12);
			
			//CINEMARK
			cineService.insertCinSed(3, 13);
			cineService.insertCinSed(3, 14);
			cineService.insertCinSed(3, 15);
			cineService.insertCinSed(3, 16);
			cineService.insertCinSed(3, 17);
			
			//CINÉPOLIS
			cineService.insertCinSed(4, 1);
			cineService.insertCinSed(4, 2);
			
	    }else {
	    		System.out.println("Ya se han vinculado las sedes a los cines");
	    }
	}

}

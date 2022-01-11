package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.service.PeliculaService;

@Component
@Order(18)
public class CreatePeliculasGeneros implements CommandLineRunner{
	
	@Autowired
	PeliculaService peliculaService;

	@Override
	public void run(String... args) throws Exception {
		if (peliculaService.listarPelGen().isEmpty()) {

			peliculaService.insertPelGen(1, 1); peliculaService.insertPelGen(1, 18);
			peliculaService.insertPelGen(2, 1); peliculaService.insertPelGen(2, 2);
			peliculaService.insertPelGen(3, 4);

		} else {
			System.out.println("Ya se han vinculado los géneros a las películas");
		}

		
	}

}

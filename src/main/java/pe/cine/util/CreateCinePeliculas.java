package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.service.CineService;

@Component
@Order(16)
public class CreateCinePeliculas implements CommandLineRunner {

	@Autowired
	CineService cineService;

	@Override
	public void run(String... args) throws Exception {
		if (cineService.listarCinPel().isEmpty()) {

			// CINEPLANET
			cineService.insertCinPel(1, 1);
			cineService.insertCinPel(1, 2);
			cineService.insertCinPel(1, 3);

			// CINESTAR
			cineService.insertCinPel(2, 1);
			cineService.insertCinPel(2, 2);

			// CINEMARK
			cineService.insertCinPel(3, 1);
			cineService.insertCinPel(3, 2);
			cineService.insertCinPel(3, 3);

			// CINÉPOLIS
			cineService.insertCinPel(4, 1);
			cineService.insertCinPel(4, 2);

		} else {
			System.out.println("Ya se han vinculado las películas a los cines");
		}
	}

}

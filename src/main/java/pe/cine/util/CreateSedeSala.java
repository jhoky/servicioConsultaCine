package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.service.SedeService;

@Component
@Order(17)
public class CreateSedeSala implements CommandLineRunner {

	@Autowired
	SedeService sedeService;

	@Override
	public void run(String... args) throws Exception {
		if (sedeService.listarSedSal().isEmpty()) {

			// CINEPLANET
			sedeService.insertSedSal(3, 1); sedeService.insertSedSal(3, 2);
			sedeService.insertSedSal(4, 1); sedeService.insertSedSal(4, 2);
			sedeService.insertSedSal(5, 1); sedeService.insertSedSal(5, 2);
			sedeService.insertSedSal(6, 1);
			sedeService.insertSedSal(7, 1); sedeService.insertSedSal(7, 2);
			
			// CINESTAR
			sedeService.insertSedSal(8, 1); sedeService.insertSedSal(8, 2);
			sedeService.insertSedSal(9, 1);
			sedeService.insertSedSal(10, 1);
			sedeService.insertSedSal(11, 1); sedeService.insertSedSal(11, 2);
			sedeService.insertSedSal(12, 1); sedeService.insertSedSal(12, 2);
			
			// CINEMARK
			sedeService.insertSedSal(13, 1); sedeService.insertSedSal(13, 2);
			sedeService.insertSedSal(14, 1); sedeService.insertSedSal(14, 2);
			sedeService.insertSedSal(15, 1); sedeService.insertSedSal(15, 2);
			sedeService.insertSedSal(16, 1); sedeService.insertSedSal(16, 2);
			sedeService.insertSedSal(17, 1);
			
			// CINÉPOLIS
			sedeService.insertSedSal(1, 1); sedeService.insertSedSal(1, 2);
			sedeService.insertSedSal(2, 1);

		} else {
			System.out.println("Ya se han vinculado las salas a las sedes");
		}

	}

}

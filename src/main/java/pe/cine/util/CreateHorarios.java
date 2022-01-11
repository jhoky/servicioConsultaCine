package pe.cine.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Cine;
import pe.cine.model.Horario;
import pe.cine.model.Pelicula;
import pe.cine.service.CineService;
import pe.cine.service.HorarioService;
import pe.cine.service.PeliculaService;


@Component
@Order(14)
public class CreateHorarios implements CommandLineRunner{
	
	@Autowired
	HorarioService serv;
	
	@Autowired
	PeliculaService peliculaService;

	@Autowired
	CineService cineService;
	
	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsById(1) && !serv.existsById(2)) {
			Cine cine= cineService.getOne(1).get();
			Pelicula pelicula = peliculaService.getOne(1).get();
	    	Horario h1 = new Horario(LocalDate.of(2022, 01, 03),"16:00",true,pelicula,cine);
	    	Horario h2 = new Horario(LocalDate.of(2022, 01, 03),"17:00",true,pelicula,cine);
	        serv.save(h1);serv.save(h2); 
	        
	        cine= cineService.getOne(2).get();
			pelicula = peliculaService.getOne(2).get();
	    	Horario h3 = new Horario(LocalDate.of(2022, 01, 04),"18:00",true,pelicula,cine);
	    	Horario h4 = new Horario(LocalDate.of(2022, 01, 04),"19:00",true,pelicula,cine);
	        serv.save(h3);serv.save(h4); 
	        
	        cine= cineService.getOne(3).get();
			pelicula = peliculaService.getOne(3).get();
	    	Horario h5 = new Horario(LocalDate.of(2022, 01, 05),"20:00",true,pelicula,cine);
	    	Horario h6 = new Horario(LocalDate.of(2022, 01, 05),"21:00",true,pelicula,cine);
	        serv.save(h5);serv.save(h6); 
	        
	        cine= cineService.getOne(4).get();
			pelicula = peliculaService.getOne(1).get();
	    	Horario h7 = new Horario(LocalDate.of(2022, 01, 04),"17:00",true,pelicula,cine);
	    	Horario h8 = new Horario(LocalDate.of(2022, 01, 04),"22:00",true,pelicula,cine);
	        serv.save(h7);serv.save(h8); 
	    }else {
	    		System.out.println("Ya se han creado los horarios");
	    }
	}

}

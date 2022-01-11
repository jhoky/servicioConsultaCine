package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Genero;
import pe.cine.service.GeneroService;


@Component
@Order(5)
public class CreateGenero implements CommandLineRunner{
	
	@Autowired
	GeneroService serv;
	
	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Acción") && !serv.existsByNombre("Western")) {
	    	Genero g1 = new Genero("Acción");Genero g2 = new Genero("Ciencia Ficción");Genero g3 = new Genero("Comedia");Genero g4 = new Genero("Drama");
	    	Genero g5 = new Genero("Fantasía");Genero g6 = new Genero("Melodrama");Genero g7 = new Genero("Musical");Genero g8 = new Genero("Romance");
	    	Genero g9 = new Genero("Suspenso");Genero g10 = new Genero("Terror");Genero g11 = new Genero("Documental");Genero g12 = new Genero("Biográfico");
	    	Genero g13 = new Genero("Histórico");Genero g14 = new Genero("Policíaco");Genero g15 = new Genero("Western");Genero g16 = new Genero("Animación");
	    	Genero g17 = new Genero("Independiente");Genero g18 = new Genero("Aventuras");Genero g19 = new Genero("Misterio");
	    	serv.save(g1); serv.save(g2); serv.save(g3); serv.save(g4); serv.save(g5); serv.save(g6); serv.save(g7); serv.save(g8); serv.save(g9); serv.save(g10);
	        serv.save(g11); serv.save(g12); serv.save(g13); serv.save(g14); serv.save(g15); serv.save(g16); serv.save(g17); serv.save(g18); serv.save(g19);
	    }else {
	    		System.out.println("Ya se han creado los géneros");
	    }
	}

}

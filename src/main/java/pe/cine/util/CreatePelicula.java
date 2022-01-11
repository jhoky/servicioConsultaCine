package pe.cine.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Clasificacion;
import pe.cine.model.Director;
import pe.cine.model.Pelicula;
import pe.cine.service.ClasificacionService;
import pe.cine.service.DirectorService;
import pe.cine.service.PeliculaService;

@Component
@Order(11)
public class CreatePelicula implements CommandLineRunner{
	
	@Autowired
	PeliculaService serv;
	
	@Autowired
	DirectorService servi;
	
	@Autowired
	ClasificacionService servii;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Spider-Man: No Way Home")) {
			Director director = servi.getOne(5).get();
			Clasificacion clasf = servii.getOne(2).get();;
	    	Pelicula p1 = new Pelicula("Spider-Man: No Way Home","Después de que Mysterio desvelara la identidad de Spider-Man a todo el mundo en Lejos de casa, Peter Parker (Tom Holland), desesperado por volver a la normalidad y recuperar su anterior vida, pide ayuda a Doctor Strange para enmendar tal acción. El Hechicero Supremo de Marvel accede a ayudar al joven Hombre Araña, sin embargo, algo sale mal y el multiverso se convierte en la mayor amenaza hasta el momento.",
	    			"02h 28m","https://res.cloudinary.com/drmoiteba/image/upload/v1639970781/hipqzgln1npm0eszrsdx.jpg",LocalDate.of(2021, 12, 16),true,director,clasf);
	    	
	    	director = servi.getOne(11).get();
			clasf = servii.getOne(3).get();
	    	Pelicula p2 = new Pelicula("Matrix Resurrecciones","Acosado por extraños recuerdos, la vida de Neo toma un giro inesperado al encontrarse, nuevamente, dentro de la Matrix.",
	    			"02h 30m","https://res.cloudinary.com/drmoiteba/image/upload/v1640391761/Matrix_nsxevy.jpg",LocalDate.of(2021, 12, 23),true,director,clasf);
	    	
	    	director = servi.getOne(12).get();
			clasf = servii.getOne(3).get();
	    	Pelicula p3 = new Pelicula("La Casa Gucci","Una mirada al asesinato de Maurizio Gucci el 27 de marzo de 1995, nieto heredero de Guccio Gucci, fundador de la famosa marca de lujo italiana. Su futura ex esposa, Patrizia Reggiani, ordenó su asesinato para recibir su parte de la herencia.",
	    			"02h 40m","https://res.cloudinary.com/drmoiteba/image/upload/v1640391761/La_Casa_Gucci_ybepmt.jpg",LocalDate.of(2021, 11, 25),true,director,clasf);
	    	
	    	serv.save(p1);serv.save(p2);serv.save(p3);
	    }else {
	    		System.out.println("Ya se han creado las películas");
	    }
	}

}

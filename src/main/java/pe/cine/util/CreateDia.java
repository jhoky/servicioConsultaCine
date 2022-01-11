package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Dia;
import pe.cine.service.DiaService;

@Component
@Order(4)
public class CreateDia implements CommandLineRunner{
	
	@Autowired
	DiaService serv;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Lunes") && !serv.existsByNombre("Domingo")) {
	    	Dia diaL = new Dia("Lunes"); Dia diaM = new Dia("Martes"); Dia diaMi = new Dia("Miércoles");
	    	Dia diaJ = new Dia("Jueves"); Dia diaV = new Dia("Viernes"); Dia diaS = new Dia("Sábado");
	    	Dia diaD = new Dia("Domingo");
	        serv.save(diaL); serv.save(diaM); serv.save(diaMi); serv.save(diaJ);serv.save(diaV);
	        serv.save(diaS); serv.save(diaD);
	    }else {
	    		System.out.println("Ya se han creado los días");
	    }
	}

}

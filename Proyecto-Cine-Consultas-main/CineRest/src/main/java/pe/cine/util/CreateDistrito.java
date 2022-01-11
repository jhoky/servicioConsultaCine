package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Distrito;
import pe.cine.service.DistritoService;

@Component
@Order(8)
public class CreateDistrito implements CommandLineRunner{
	
	@Autowired
	DistritoService serv;

	@Override
	public void run(String... args) throws Exception {
		
		if(!serv.existsByNombre("Puente Piedra") && !serv.existsByNombre("Pachacamac")) {
	    	Distrito dis1 = new Distrito("Ancón"); Distrito dis2 = new Distrito("Ate Vitarte"); Distrito dis3 = new Distrito("Barranco"); Distrito dis4 = new Distrito("Breña");
	    	Distrito dis5 = new Distrito("Carabayllo"); Distrito dis6 = new Distrito("Chaclacayo"); Distrito dis7 = new Distrito("Chorrillos"); Distrito dis8 = new Distrito("Cieneguilla");
	    	Distrito dis9 = new Distrito("Comas"); Distrito dis10 = new Distrito("El Agustino"); Distrito dis11 = new Distrito("Independencia"); Distrito dis12 = new Distrito("Jesús María");
	    	Distrito dis13 = new Distrito("La Molina"); Distrito dis14 = new Distrito("La Victoria"); Distrito dis15 = new Distrito("Lima"); Distrito dis16 = new Distrito("Lince");
	    	Distrito dis17 = new Distrito("Los Olivos"); Distrito dis18 = new Distrito("Lurigancho"); Distrito dis19 = new Distrito("Lurín"); Distrito dis20 = new Distrito("Magdalena del Mar");
	    	Distrito dis21 = new Distrito("Miraflores"); Distrito dis22 = new Distrito("Pachacamac"); Distrito dis23 = new Distrito("Pucusana"); Distrito dis24 = new Distrito("Pueblo Libre");
	    	Distrito dis25 = new Distrito("Puente Piedra"); Distrito dis26 = new Distrito("Punta Hermosa"); Distrito dis27 = new Distrito("Punta Negra"); Distrito dis28 = new Distrito("Rímac");
	    	Distrito dis29 = new Distrito("San Bartolo"); Distrito dis30 = new Distrito("San Borja"); Distrito dis31 = new Distrito("San Isidro"); Distrito dis32 = new Distrito("San Juan de Lurigancho");
	    	Distrito dis33 = new Distrito("San Juan de Miraflores"); Distrito dis34 = new Distrito("San Luis"); Distrito dis35 = new Distrito("San Martín de Porres"); Distrito dis36 = new Distrito("San Miguel");
	    	Distrito dis37 = new Distrito("Santa Anita"); Distrito dis38 = new Distrito("Santa María del Mar"); Distrito dis39 = new Distrito("Santa Rosa"); Distrito dis40 = new Distrito("Santiago de Surco");
	    	Distrito dis41 = new Distrito("Surquillo"); Distrito dis42 = new Distrito("Villa El Salvador"); Distrito dis43 = new Distrito("Villa María del Triunfo");
	        serv.save(dis1);serv.save(dis2);serv.save(dis3);serv.save(dis4);serv.save(dis5);serv.save(dis6);serv.save(dis7);serv.save(dis8);serv.save(dis9);
	        serv.save(dis10);serv.save(dis11);serv.save(dis12);serv.save(dis13);serv.save(dis14);serv.save(dis15);serv.save(dis16);serv.save(dis17);serv.save(dis18);
	        serv.save(dis19);serv.save(dis20);serv.save(dis21);serv.save(dis22);serv.save(dis23);serv.save(dis24);serv.save(dis25);serv.save(dis26);serv.save(dis27);
	        serv.save(dis28);serv.save(dis29);serv.save(dis30);serv.save(dis31);serv.save(dis32);serv.save(dis33);serv.save(dis34);serv.save(dis35);serv.save(dis36);
	        serv.save(dis37);serv.save(dis38);serv.save(dis39);serv.save(dis40);serv.save(dis41);serv.save(dis42);serv.save(dis43);
	    }else {
	    		System.out.println("Ya se han creado los distritos");
	    }
		
	}

}

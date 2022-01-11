package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Distrito;
import pe.cine.model.Sede;
import pe.cine.service.DistritoService;
import pe.cine.service.SedeService;

@Component
@Order(9)
public class CreateSede implements CommandLineRunner{
	
	@Autowired
	SedeService serv;
	
	@Autowired
	DistritoService servi;

	
	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByNombre("Cinépolis Plaza Norte") && !serv.existsByNombre("Cinépolis Santa Anita")) {
			//CINEPOLIS
			Distrito distrito = servi.getOne(11).get();
	    	Sede sede1 = new Sede("Cinépolis Plaza Norte","Avenida Alfredo Mendiola (Panamericana Norte) cruce con Tomás Valle, S/N, Interior Tercer Nivel Independencia",
	    			"-12.007649014090068, -77.05869427012198",14,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639965736/gh8j5zv5qidb1w9vojuk.jpg"); 
	    	serv.save(sede1); 
	    	distrito = servi.getOne(37).get();
	    	Sede sede2 = new Sede("Cinépolis Santa Anita","Av. Carretera central 111, Santa Anita, Centro Comercial Mall Aventura Plaza",
	    			"-12.057088632186794, -76.97116832349516",11,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639985941/Cinepolis_Santa_Anita_qcnx2g.jpg"); 
	    	serv.save(sede2); 
	    	//CINEPLANET
	    	distrito = servi.getOne(21).get();
	    	Sede sede3 = new Sede("Cineplanet Alcázar","Av. Mariscal Andres de Sta. Cruz 814",
	    			"-12.110325279501698, -77.0375503904403",8,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639986564/Cineplanet_Alcazar_fzp3ka.jpg"); 
	    	serv.save(sede3); 
	    	distrito = servi.getOne(40).get();
	    	Sede sede4 = new Sede("Cineplanet Caminos del Inca","Jirón Monterrey 170, Santiago de Surco 15038",
	    				"-12.113429284137453, -76.99125366031761",7,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639987398/q3bfeancncjxgfq5n2b6.jpg"); 
	    	serv.save(sede4);
	    	distrito = servi.getOne(15).get();
	    	Sede sede5 = new Sede("Cineplanet Lurin","Av. San Pedro 33, Lima 15037",
	    				"-12.274596636092417, -76.87401336156738",3,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639988038/Cineplanet_Lurin_hwplyh.jpg"); 
	    	serv.save(sede5);
	    	distrito = servi.getOne(12).get();
	    	Sede sede6 = new Sede("Cineplanet Salaverry","Av. Gral. Salaverry 2370, Lima 15076",
	    				"-12.089864422183298, -77.05266591797287",9,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639988038/Cineplanet_Salaverry_w1p9m2.jpg"); 
	    	serv.save(sede6);
	    	distrito = servi.getOne(33).get();
	    	Sede sede7 = new Sede("Cineplanet Mall del Sur","Carr. Atocongo, San Juan de Miraflores 15801",
	    				"-12.15592905794634, -76.98160716156852",13,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639988038/Cineplanet_Mall_del_Sur_sydv8w.jpg"); 
	    	serv.save(sede7);
	    	//CINESTAR
	    	distrito = servi.getOne(40).get();
	    	Sede sede8 = new Sede("Cinestar Benavides","Avenida Alfredo Benavides Nº 4981 Santiago de Surco",
	    				"-12.12818441591721, -76.98537438979233",8,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639989331/Cinestar_Benavides_cyyya0.jpg"); 
	    	serv.save(sede8);
	    	distrito = servi.getOne(32).get();
	    	Sede sede9 = new Sede("Cinestar San Juan","Av. Los Próceres de la Independencia 1632 - San Juan de Lurigancho",
	    				"-12.005976643583887, -77.00492780018183",5,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639989331/Cinestar_San_Juan_de_Lurigancho_miw4un.jpg"); 
	    	serv.save(sede9);
	    	distrito = servi.getOne(30).get();
	    	Sede sede10 = new Sede("Cinestar Aviacion","Calle Tiziano 100 Alt. Cuadra 24 de la Av. Aviación",
	    				"-12.088988122191916, -77.003239235764",7,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639989331/Cinestar_Aviacion_r3ilfq.jpg"); 
	    	serv.save(sede10);
	    	distrito = servi.getOne(9).get();
	    	Sede sede11 = new Sede("Cinestar Comas","Av.Tupac Amaru Cuadra 39 S/N - La Pascana (Altura de Plaza Vea)",
	    				"-11.93228868767345, -77.04372968557004",6,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639989331/Cinestar_Comas_hvdvqh.jpg"); 
	    	serv.save(sede11);
	    	distrito = servi.getOne(15).get();
	    	Sede sede12 = new Sede("Cinestar Excelsior","Jirón de la Unión Nº 780 - Cercado de Lima (a 2 cuadras de la plaza San Martin)",
	    				"-12.04957120302185, -77.03399303215744",4,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639989331/Cinestar_Excelsior_yh5fcg.jpg"); 
	    	serv.save(sede12);
	    	//CINEMARK
	    	distrito = servi.getOne(41).get();
	    	Sede sede13 = new Sede("Cinemark Angamos","Av. Angamos Nº 1803 y/o Av. Tomas Marsano Nº 961- Surquillo",
	    				"-12.111508302677263, -77.01267763212117",7,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639990886/Cinemark_Angamos_nh97le.jpg"); 
	    	serv.save(sede13);
	    	distrito = servi.getOne(40).get();
	    	Sede sede14 = new Sede("Cinemark Jockey Plaza","Av. Javier Prado Este 4200 Monterrico Surco",
	    				"-12.085404312110839, -76.97551372107681",6,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639990885/Cinemark_Jockey_Plaza_ye8c60.jpg"); 
	    	serv.save(sede14);
	    	distrito = servi.getOne(14).get();
	    	Sede sede15 = new Sede("Cinemark Gamarra","Avenida Aviación 950 La Victoria, LIMA 13",
	    				"-12.070690547177927, -77.01213840267377",6,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639990886/Cinemark_Gamarra_pzvlsf.png"); 
	    	serv.save(sede15);
	    	distrito = servi.getOne(11).get();
	    	Sede sede16 = new Sede("Cinemark Megaplaza","Calle Alfredo Mendiola 3698 Km 8.5 de la Av. Panamericana Norte Independencia",
	    				"-11.993371647418076, -77.06115203212234",7,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1640387421/Cinemark_Megaplaza_gm1q3w.jpg"); 
	    	serv.save(sede16);
	    	distrito = servi.getOne(9).get();
	    	Sede sede17 = new Sede("Cinemark Comas","MallPlaza, Ca. 2 126, Comas 15314",
	    				"-11.936119003649235, -77.0663526026751",9,true,distrito,"https://res.cloudinary.com/drmoiteba/image/upload/v1639990885/Cinemark_COmas_buzhlz.jpg"); 
	    	serv.save(sede17);
	    }else {
	    		System.out.println("Ya se han creado las sedes");
	    }
	}

}

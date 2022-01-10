package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.model.Cine;
import pe.cine.model.Dia;
import pe.cine.model.Precio_Boleteria;
import pe.cine.model.Tipo_Publico;
import pe.cine.model.Tipo_Sala;
import pe.cine.service.CineService;
import pe.cine.service.DiaService;
import pe.cine.service.PrecioBoleteriaService;
import pe.cine.service.TipoPublicoService;
import pe.cine.service.TipoSalaService;

@Component
@Order(13)
public class CreatePrecios implements CommandLineRunner{
	
	@Autowired
	PrecioBoleteriaService serv;
	
	@Autowired
	DiaService diaService;
	
	@Autowired
	CineService cineService;
	
	@Autowired
	TipoSalaService salaService;
	
	@Autowired
	TipoPublicoService publicoService;

	@Override
	public void run(String... args) throws Exception {
		
		if(!serv.existsById(1)) {
			
			//CINEPLANET --- 2D
			Cine cine = cineService.getOne(1).get();
			Tipo_Sala sala = salaService.getOne(1).get();
			
			//GENERAL
			Tipo_Publico publico = publicoService.getOne(1).get();
			//LUNES
			Dia dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec1 = new Precio_Boleteria(32.0,dia,publico,sala,cine);
	    	serv.save(prec1);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec3 = new Precio_Boleteria(19.0,dia,publico,sala,cine);
	    	serv.save(prec3);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec5 = new Precio_Boleteria(24.0,dia,publico,sala,cine);
	    	serv.save(prec5);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec7 = new Precio_Boleteria(32.0,dia,publico,sala,cine);
	    	serv.save(prec7);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec9 = new Precio_Boleteria(32.0,dia,publico,sala,cine);
	    	serv.save(prec9);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec11 = new Precio_Boleteria(32.0,dia,publico,sala,cine);
	    	serv.save(prec11);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec13 = new Precio_Boleteria(32.0,dia,publico,sala,cine);
	    	serv.save(prec13);
	    	
	    	//NIÑOS Y ADULTOS MAYORES
			publico = publicoService.getOne(2).get();
	    	//LUNES
	    	dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec2 = new Precio_Boleteria(30.0,dia,publico,sala,cine);
	    	serv.save(prec2);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec4 = new Precio_Boleteria(19.0,dia,publico,sala,cine);
	    	serv.save(prec4);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec6 = new Precio_Boleteria(22.0,dia,publico,sala,cine);
	    	serv.save(prec6);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec8 = new Precio_Boleteria(30.0,dia,publico,sala,cine);
	    	serv.save(prec8);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec10 = new Precio_Boleteria(30.0,dia,publico,sala,cine);
	    	serv.save(prec10);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec12 = new Precio_Boleteria(30.0,dia,publico,sala,cine);
	    	serv.save(prec12);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec14 = new Precio_Boleteria(30.0,dia,publico,sala,cine);
	    	serv.save(prec14);
	    	
	    	//CINESTAR --- 2D
			cine = cineService.getOne(2).get();
			sala = salaService.getOne(1).get();
			
			//GENERAL
			publico = publicoService.getOne(1).get();
			//LUNES
			dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec15 = new Precio_Boleteria(13.0,dia,publico,sala,cine);
	    	serv.save(prec15);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec16 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec16);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec17 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec17);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec18 = new Precio_Boleteria(11.50,dia,publico,sala,cine);
	    	serv.save(prec18);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec19 = new Precio_Boleteria(13.0,dia,publico,sala,cine);
	    	serv.save(prec19);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec20 = new Precio_Boleteria(12.50,dia,publico,sala,cine);
	    	serv.save(prec20);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec21 = new Precio_Boleteria(12.50,dia,publico,sala,cine);
	    	serv.save(prec21);
	    	
	    	//NIÑOS Y ADULTOS MAYORES
			publico = publicoService.getOne(2).get();
	    	//LUNES
	    	dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec22 = new Precio_Boleteria(13.0,dia,publico,sala,cine);
	    	serv.save(prec22);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec23 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec23);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec24 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec24);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec25 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec25);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec26 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec26);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec27 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec27);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec28 = new Precio_Boleteria(9.50,dia,publico,sala,cine);
	    	serv.save(prec28);
	    	
	    	//CINÉPOLIS --- 2D
			cine = cineService.getOne(4).get();
			sala = salaService.getOne(1).get();
			
			//GENERAL
			publico = publicoService.getOne(1).get();
			//LUNES
			dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec29 = new Precio_Boleteria(17.50,dia,publico,sala,cine);
	    	serv.save(prec29);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec30 = new Precio_Boleteria(19.00,dia,publico,sala,cine);
	    	serv.save(prec30);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec31 = new Precio_Boleteria(17.50,dia,publico,sala,cine);
	    	serv.save(prec31);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec32 = new Precio_Boleteria(21.00,dia,publico,sala,cine);
	    	serv.save(prec32);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec33 = new Precio_Boleteria(21.0,dia,publico,sala,cine);
	    	serv.save(prec33);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec34 = new Precio_Boleteria(21.00,dia,publico,sala,cine);
	    	serv.save(prec34);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec35 = new Precio_Boleteria(21.00,dia,publico,sala,cine);
	    	serv.save(prec35);
	    	
	    	//NIÑOS Y ADULTOS MAYORES
			publico = publicoService.getOne(2).get();
	    	//LUNES
	    	dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec36 = new Precio_Boleteria(16.50,dia,publico,sala,cine);
	    	serv.save(prec36);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec37 = new Precio_Boleteria(19.00,dia,publico,sala,cine);
	    	serv.save(prec37);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec38 = new Precio_Boleteria(16.50,dia,publico,sala,cine);
	    	serv.save(prec38);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec39 = new Precio_Boleteria(18.50,dia,publico,sala,cine);
	    	serv.save(prec39);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec40 = new Precio_Boleteria(18.50,dia,publico,sala,cine);
	    	serv.save(prec40);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec41 = new Precio_Boleteria(18.50,dia,publico,sala,cine);
	    	serv.save(prec41);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec42 = new Precio_Boleteria(18.50,dia,publico,sala,cine);
	    	serv.save(prec42);
	    	
	    	//CINEMARK --- 2D
			cine = cineService.getOne(3).get();
			sala = salaService.getOne(1).get();
			
			//GENERAL
			publico = publicoService.getOne(1).get();
			//LUNES
			dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec43 = new Precio_Boleteria(25.50,dia,publico,sala,cine);
	    	serv.save(prec43);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec44 = new Precio_Boleteria(18.80,dia,publico,sala,cine);
	    	serv.save(prec44);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec45 = new Precio_Boleteria(25.50,dia,publico,sala,cine);
	    	serv.save(prec45);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec46 = new Precio_Boleteria(29.80,dia,publico,sala,cine);
	    	serv.save(prec46);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec47 = new Precio_Boleteria(29.80,dia,publico,sala,cine);
	    	serv.save(prec47);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec48 = new Precio_Boleteria(31.80,dia,publico,sala,cine);
	    	serv.save(prec48);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec49 = new Precio_Boleteria(30.80,dia,publico,sala,cine);
	    	serv.save(prec49);
	    	
	    	//NIÑOS Y ADULTOS MAYORES
			publico = publicoService.getOne(2).get();
	    	//LUNES
	    	dia = diaService.getOne(1).get();
	    	Precio_Boleteria prec50 = new Precio_Boleteria(23.00,dia,publico,sala,cine);
	    	serv.save(prec50);
	    	//MARTES
	    	dia = diaService.getOne(2).get();
	    	Precio_Boleteria prec51 = new Precio_Boleteria(18.80,dia,publico,sala,cine);
	    	serv.save(prec51);
	    	//MIERCOLES
	    	dia = diaService.getOne(3).get();
	    	Precio_Boleteria prec52 = new Precio_Boleteria(23.00,dia,publico,sala,cine);
	    	serv.save(prec52);
	    	//JUEVES
	    	dia = diaService.getOne(4).get();
	    	Precio_Boleteria prec53 = new Precio_Boleteria(26.30,dia,publico,sala,cine);
	    	serv.save(prec53);
	    	//VIERNES
	    	dia = diaService.getOne(5).get();
	    	Precio_Boleteria prec54 = new Precio_Boleteria(26.30,dia,publico,sala,cine);
	    	serv.save(prec54);
	    	//SÁBADO
	    	dia = diaService.getOne(6).get();
	    	Precio_Boleteria prec55 = new Precio_Boleteria(29.30,dia,publico,sala,cine);
	    	serv.save(prec55);
	    	//DOMINGO
	    	dia = diaService.getOne(7).get();
	    	Precio_Boleteria prec56 = new Precio_Boleteria(28.30,dia,publico,sala,cine);
	    	serv.save(prec56);
	    	
	    }else {
	    		System.out.println("Ya se han creado los precios");
	    }
	}

}

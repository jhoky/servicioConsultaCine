package pe.cine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import pe.cine.enums.RolNombre;
import pe.cine.model.Rol;
import pe.cine.service.RolService;

@Component
@Order(1)
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Override
    public void run(String... args) throws Exception {
    	if(!rolService.existsByRolNombre(RolNombre.ROLE_ADMIN)) {
    	Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN); Rol rolUser = new Rol(RolNombre.ROLE_USER);Rol rolSupadmin = new Rol(RolNombre.ROLE_SUPADMIN);
        rolService.save(rolAdmin); rolService.save(rolUser); rolService.save(rolSupadmin);	
    	}else {
    		System.out.println("Ya se han creado los roles");
    	}

}}

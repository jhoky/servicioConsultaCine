package pe.cine.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pe.cine.enums.RolNombre;
import pe.cine.model.Rol;
import pe.cine.model.Usuario;
import pe.cine.service.RolService;
import pe.cine.service.UsuarioService;

@Component
@Order(12)
public class CreateUser implements CommandLineRunner{
	
	@Autowired
	UsuarioService serv;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		if(!serv.existsByUsuario("angelt")) {
			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUPADMIN).get());
	    	Usuario us = new Usuario("Angel Tasayco","angelt","angel@gmail.com",passwordEncoder.encode("Barcelona10"), true); 
	    	us.setRoles(roles);
	        serv.save(us); 
	    }else {
	    		System.out.println("Ya se han creado el usuario");
	    }
	}

}

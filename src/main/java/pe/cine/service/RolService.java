package pe.cine.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.enums.RolNombre;
import pe.cine.model.Rol;
import pe.cine.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	private RolRepository repository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return repository.findByRolNombre(rolNombre);
	}
	
	public void save(Rol rol) {
		repository.save(rol);
	}
	
	public boolean existsByRolNombre(RolNombre rolNombre){
        return repository.existsByRolNombre(rolNombre);
    }

}

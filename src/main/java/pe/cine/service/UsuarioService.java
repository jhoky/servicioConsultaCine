package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Usuario;
import pe.cine.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> lista(){
        return repository.lista();
    }
	
	public Optional<Usuario> getOne(int id){
        return repository.findById(id);
    }

	public Optional<Usuario> getByUsuario(String nombreUsuario){
		return repository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByUsuario(String nombreUsuario) {
		return repository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
	
	public boolean existsById(int id){
        return repository.existsById(id);
    }
	
	public void save(Usuario usuario) {
		repository.save(usuario);
	}
	
	public Usuario buscarporemail(String email) {
		return repository.buscarporemail(email);
	}
	

}

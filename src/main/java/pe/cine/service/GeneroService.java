package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Genero;
import pe.cine.repository.GeneroRepository;

@Service
@Transactional
public class GeneroService {

	@Autowired
	private GeneroRepository repository;
	
	public List<Genero> list(){
        return repository.findAll();
    }

    public Optional<Genero> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Genero> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Genero genero){
    	repository.save(genero);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return repository.existsByNombre(nombre);
    }

}

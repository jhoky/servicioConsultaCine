package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Director;
import pe.cine.repository.DirectorRepository;

@Service
@Transactional
public class DirectorService {

	@Autowired
	private DirectorRepository repository;

	public List<Director> list(){
        return repository.findAll();
    }

    public Optional<Director> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Director> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Director director){
    	repository.save(director);
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

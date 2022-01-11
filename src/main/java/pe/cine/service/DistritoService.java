package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Distrito;
import pe.cine.repository.DistritoRepository;

@Service
@Transactional
public class DistritoService{
	
	@Autowired
	DistritoRepository repository;
	
	public List<Distrito> list(){
        return repository.findAll();
    }

    public Optional<Distrito> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Distrito> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Distrito distrito){
    	repository.save(distrito);
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

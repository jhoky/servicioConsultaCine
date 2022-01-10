package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Cine;
import pe.cine.repository.CineRepository;

@Service
@Transactional
public class CineService{
	
	@Autowired
	private CineRepository repository;

	public List<Cine> list(){
        return repository.findAll();
    }

    public Optional<Cine> getOne(int id){
        return repository.findById(id);
    }
	
	public int getId(){
    	return repository.obtenerId();
    }

    
    public Optional<Cine> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Cine cine){
    	repository.save(cine);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }
	
	public void deleteCinSed(int cine, int sede) {
    	repository.deleteCinSed(cine, sede);
    }
    
    public void deleteCinPel(int cine, int pelicula) {
    	repository.deleteCinPel(cine, pelicula);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return repository.existsByNombre(nombre);
    }
	
}

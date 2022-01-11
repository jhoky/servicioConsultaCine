package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Clasificacion;
import pe.cine.repository.ClasificacionRepository;

@Service
@Transactional
public class ClasificacionService{

	
	@Autowired
	private ClasificacionRepository repository;

	public List<Clasificacion> list(){
        return repository.findAll();
    }

    public Optional<Clasificacion> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Clasificacion> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Clasificacion clasificacion){
    	repository.save(clasificacion);
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

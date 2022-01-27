package pe.cine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Sede;
import pe.cine.repository.SedeRepository;

@Service
@Transactional
public class SedeService {

    @Autowired
    private SedeRepository repository;
	
	public List<Sede> listDesc() {
		return repository.listDesc();
	}
	
	public List<Sede> listActivos() {
		return repository.listActivos();
	}

    public List<Sede> list(){
        return repository.findAll();
    }

    public Optional<Sede> getOne(int id){
        return repository.findById(id);
    }
	
    public int getId(){
    	return repository.obtenerId();
    }

    public Optional<Sede> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Sede sede){
    	repository.save(sede);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }
	
	public void deleteSedSal(int sede, int sala) {
		repository.deleteSedSal(sede, sala);
	}
	
	public void insertSedSal(int sede, int sala) {
    	repository.insertSedSal(sede, sala);
    }
    
    public List<?> listarSedSal() {
    	return repository.listarSedSal();
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return repository.existsByNombre(nombre);
    }
    
    public ArrayList<String> buscartiposala(int sede_id) {
    return repository.buscartiposala(sede_id);
    
    }
    
    
}

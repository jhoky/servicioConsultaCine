package pe.cine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Horario;
import pe.cine.model.Pelicula;
import pe.cine.repository.HorarioRepository;
import pe.cine.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaService {

	@Autowired
	private PeliculaRepository repository;
	
	@Autowired
	private HorarioRepository repositoryhorario;
	

	public List<Pelicula> list(){
        return repository.findAll();
    }

    public Optional<Pelicula> getOne(int id){
        return repository.findById(id);
    }
	
    public int getId(){
    	return repository.obtenerId();
    }

    public Optional<Pelicula> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Pelicula pelicula){
    	repository.save(pelicula);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }
	
	public void deletePelGen(int pelicula, int genero) {
    	repository.deletePelGen(pelicula, genero);
    }
	
	public void insertPelGen(int pelicula, int genero) {
    	repository.insertPelGen(pelicula, genero);
    }
    
    public List<?> listarPelGen() {
    	return repository.listarPelGen();
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return repository.existsByNombre(nombre);
    }
    
    
    //Consulta personalizada de película
    
    public List<Pelicula> listarCartelera(){
    	return repository.listarCartelera();
    }
    
    
    //consulta horarios 
    
    public ArrayList<Horario> listahorariospelicula(int id,int idcine){
    	return repositoryhorario.listahorariosporPelicula(id,idcine);
    }
    
    
    
    
}

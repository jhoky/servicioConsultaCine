package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Dia;
import pe.cine.repository.DiaRepository;

@Service
@Transactional
public class DiaService {

	@Autowired
	private DiaRepository repository;
	
	public List<Dia> listAsc(){
        return repository.listAsc();
    }

	public List<Dia> list(){
        return repository.findAll();
    }

    public Optional<Dia> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Dia> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Dia dia){
    	repository.save(dia);
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

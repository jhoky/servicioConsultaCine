package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Tipo_Sala;
import pe.cine.repository.TipoSalaRepository;

@Service
@Transactional
public class TipoSalaService {

	@Autowired
	private TipoSalaRepository repository;

	public List<Tipo_Sala> list(){
        return repository.findAll();
    }

    public Optional<Tipo_Sala> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Tipo_Sala> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Tipo_Sala tipo_sala){
    	repository.save(tipo_sala);
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

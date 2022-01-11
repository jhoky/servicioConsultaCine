package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Tipo_Publico;
import pe.cine.repository.TipoPublicoRepository;

@Service
@Transactional
public class TipoPublicoService {

	@Autowired
	private TipoPublicoRepository repository;

	public List<Tipo_Publico> list(){
        return repository.findAll();
    }

    public Optional<Tipo_Publico> getOne(int id){
        return repository.findById(id);
    }

    public Optional<Tipo_Publico> getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }

    public void save(Tipo_Publico tipo_publico){
    	repository.save(tipo_publico);
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

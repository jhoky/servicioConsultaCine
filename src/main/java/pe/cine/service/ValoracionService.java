package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Valoracion;
import pe.cine.repository.ValoracionRepository;

@Service
@Transactional
public class ValoracionService {

	@Autowired
	private ValoracionRepository repository;

	public List<Valoracion> list(){
        return repository.findAll();
    }

    public Optional<Valoracion> getOne(int id){
        return repository.findById(id);
    }

    public void save(Valoracion valoracion){
    	repository.save(valoracion);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }
}

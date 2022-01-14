package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Precio_Boleteria;
import pe.cine.repository.PrecioBoleteriaRepository;

@Service
@Transactional
public class PrecioBoleteriaService {

	@Autowired
	private PrecioBoleteriaRepository repository;

	public List<Precio_Boleteria> list(){
        return repository.findAll();
    }
	
	public List<Precio_Boleteria> listarFiltros(int cine, int publico, int sala){
        return repository.buscarFiltro(cine, publico, sala);
    }
	
	public List<?> listarPrecio(int cine, int dia, int publico, int sala) {
		return repository.listarPrecio(cine, dia, publico, sala);
	}

    public Optional<Precio_Boleteria> getOne(int id){
        return repository.findById(id);
    }

    public void save(Precio_Boleteria precio_boleteria){
    	repository.save(precio_boleteria);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }
    
    public List<Precio_Boleteria> buscarprecioporcine(int cine_id){
    	return repository.buscarprecioporcine(cine_id);
    }
}

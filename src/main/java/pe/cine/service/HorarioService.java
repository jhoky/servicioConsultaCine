package pe.cine.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.Horario;
import pe.cine.repository.HorarioRepository;

@Service
@Transactional
public class HorarioService {

	@Autowired
	private HorarioRepository repository;

	public List<Horario> list(){
        return repository.findAll();
    }
	
	public List<Horario> listarFiltros(int cine, int pelicula, String fech){
        return repository.buscarFiltro(pelicula, cine, fech);
    }
	
	public List<?> listarHoras(int cine, int pelicula, LocalDate fecha, String hora){
		return repository.listarHoras(cine, pelicula, fecha, hora);
	}

    public Optional<Horario> getOne(int id){
        return repository.findById(id);
    }

    public void save(Horario horario){
    	repository.save(horario);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }
}

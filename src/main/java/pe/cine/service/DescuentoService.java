package pe.cine.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.cine.model.CodigoDescuento;
import pe.cine.repository.DescuentoRepository;


@Service
@Transactional
public class DescuentoService {
	
	@Autowired
	DescuentoRepository repository;
	
	public List<CodigoDescuento> list(){
        return repository.findAll();
    }

    public Optional<CodigoDescuento> getOne(int id){
        return repository.findById(id);
    }

    public void save(CodigoDescuento codigoDescuento){
    	repository.save(codigoDescuento);
    }

    public void delete(int id){
    	repository.deleteById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }
	
public CodigoDescuento buscarcodigo(String email){
        return repository.buscarcodigodescuento(email);
    }
	

}

package pe.cine.dto;

import java.io.Serializable;

import pe.cine.model.Cine;
import pe.cine.model.Pelicula;

public class CinePelicula implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Pelicula pelicula;
	
	private Cine cine;
	
	public CinePelicula() {
		
	}

	public CinePelicula(Pelicula pelicula, Cine cine) {
		super();
		this.pelicula = pelicula;
		this.cine = cine;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}
	
	

}

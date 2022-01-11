package pe.cine.dto;

import java.io.Serializable;

import pe.cine.model.Genero;
import pe.cine.model.Pelicula;

public class PeliculaGenero implements Serializable {


	private static final long serialVersionUID = 1L;

	private Pelicula pelicula;
	
	private Genero genero;
	
	public PeliculaGenero() {
		
	}

	public PeliculaGenero(Pelicula pelicula, Genero genero) {
		super();
		this.pelicula = pelicula;
		this.genero = genero;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	

}

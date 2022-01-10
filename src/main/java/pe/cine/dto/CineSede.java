package pe.cine.dto;

import java.io.Serializable;

import pe.cine.model.Cine;
import pe.cine.model.Sede;

public class CineSede implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Cine cine;
	
	private Sede sede;

	public CineSede() {
		
	}

	public CineSede(Cine cine, Sede sede) {
		super();
		this.cine = cine;
		this.sede = sede;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	

}

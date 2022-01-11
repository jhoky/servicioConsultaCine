package pe.cine.dto;

import java.io.Serializable;

import pe.cine.model.Sede;
import pe.cine.model.Tipo_Sala;

public class SedeTipoSala implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Sede sede;
	
	private Tipo_Sala tipo_sala;

	public SedeTipoSala() {
		
	}

	public SedeTipoSala(Sede sede, Tipo_Sala tipo_sala) {
		super();
		this.sede = sede;
		this.tipo_sala = tipo_sala;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Tipo_Sala getTipo_sala() {
		return tipo_sala;
	}

	public void setTipo_sala(Tipo_Sala tipo_sala) {
		this.tipo_sala = tipo_sala;
	}
	
	

}

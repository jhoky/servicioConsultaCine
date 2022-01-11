package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "genero")
@ApiModel(description = "Registro de los Géneros de las Películas")
public class Genero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@ApiModelProperty(notes = "Identificador único",required = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genero_id;
	
	@Column(length = 25, nullable = false, unique = true)
	private String nombre;
	
	public Genero() {
	}
	

	public Genero(String nombre) {
		super();
		this.nombre = nombre;
	}



	public int getGenero_id() {
		return genero_id;
	}



	public void setGenero_id(int genero_id) {
		this.genero_id = genero_id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

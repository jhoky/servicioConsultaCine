package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "director")
@ApiModel(description  = "Registro de los Directores de las Películas ")
public class Director implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int director_id;
	
	
	@Column(length = 40, nullable = false)
	private String nombre;
	
	public Director() {
	}


	public Director(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getDirector_id() {
		return director_id;
	}


	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "clasificacion")
@ApiModel(description = "Registro de las Clasificaciones de Edad de las Películas")
public class Clasificacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int clasificacion_id;
	
	
	@Column(length = 3, nullable=false, unique = true)
	private String nombre;
	
	public Clasificacion() {
	}
	

	public Clasificacion(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getClasificacion_id() {
		return clasificacion_id;
	}


	public void setClasificacion_id(int clasificacion_id) {
		this.clasificacion_id = clasificacion_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

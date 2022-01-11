package pe.cine.model;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "distrito")
@ApiModel(description  = "Registro de los Distritos")
public class Distrito implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int distrito_id;
	
	@Column(length = 50, nullable=false, unique = true)
	private String nombre;
	
	public Distrito() {
	}
	

	public Distrito(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getDistrito_id() {
		return distrito_id;
	}


	public void setDistrito_id(int distrito_id) {
		this.distrito_id = distrito_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

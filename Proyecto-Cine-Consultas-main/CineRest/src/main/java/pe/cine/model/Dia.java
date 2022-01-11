package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "dia")
@ApiModel(description  = "Registro de los Días")
public class Dia implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int dia_id;
	
	@Column(length = 9, nullable=false, unique = true)
	private String nombre;
	
	
	public Dia() {
		
	}	

	public Dia(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getDia_id() {
		return dia_id;
	}


	public void setDia_id(int dia_id) {
		this.dia_id = dia_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

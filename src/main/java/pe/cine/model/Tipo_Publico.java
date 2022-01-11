package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tipo_publico")
@ApiModel(description  = "Registro de los Tipos de Público")
public class Tipo_Publico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int tipo_publico_id;
	
	@Column(length = 40, nullable = false, unique = true)
	private String nombre;
	
	public Tipo_Publico() {
	}


	public Tipo_Publico(String nombre) {
		super();
		this.nombre = nombre;
	}


	public int getTipo_publico_id() {
		return tipo_publico_id;
	}


	public void setTipo_publico_id(int tipo_publico_id) {
		this.tipo_publico_id = tipo_publico_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}

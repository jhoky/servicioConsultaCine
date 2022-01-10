package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tipo_sala")
@ApiModel(description  = "Registro de los Tipos de Sala")
public class Tipo_Sala implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int tipo_sala_id;
	
	
	@Column(length = 15, nullable = false, unique = true)
	private String nombre;
	
	public Tipo_Sala() {
	}
	
	
	public Tipo_Sala(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getTipo_sala_id() {
		return tipo_sala_id;
	}

	public void setTipo_sala_id(int tipo_sala_id) {
		this.tipo_sala_id = tipo_sala_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}

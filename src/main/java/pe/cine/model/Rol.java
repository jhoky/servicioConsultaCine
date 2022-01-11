package pe.cine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pe.cine.enums.RolNombre;


@Entity
@Table(name = "roles")
@ApiModel(description  = "Registro de los Roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true, length = 20, nullable = false)
	private RolNombre rolNombre;

	public Rol() {
		
	}

	public Rol(RolNombre rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
	

}

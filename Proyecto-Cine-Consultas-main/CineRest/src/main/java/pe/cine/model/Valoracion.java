package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "valoracion")
@ApiModel(description  = "Registro de las Valoraciones Hechas por el Usuario")
public class Valoracion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int valoracion_id;
	
	@Column(nullable = false)
	private int puntuacion;
	
	@ManyToOne
	@JoinColumn(name= "fk_sede_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_sede_id) references sede (sede_id)"))
	private Sede sede;
	
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_usuario_id) references usuario (usuario_id)"))
	private Usuario usuario;
	
	public Valoracion() {
	}


	public int getValoracion_id() {
		return valoracion_id;
	}


	public void setValoracion_id(int valoracion_id) {
		this.valoracion_id = valoracion_id;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	public Sede getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede = sede;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

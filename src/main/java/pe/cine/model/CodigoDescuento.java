package pe.cine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "codigo_descuento")
@ApiModel(description  = "Registro de los Códigos de Descuento")
public class CodigoDescuento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int descuento_id;
	
	@Column(nullable=false)
	private int nr_descuento;
	
	@OneToOne
	@JoinColumn(name = "usuario_id",nullable = false,unique = true,
	            foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(usuario_id) references usuario(usuario_id)"))
	private Usuario usuario;
	
	public CodigoDescuento() {
		
	}

	public CodigoDescuento(int nr_descuento, Usuario usuario) {
		super();
		this.nr_descuento = nr_descuento;
		this.usuario = usuario;
	}

	public int getDescuento_id() {
		return descuento_id;
	}

	public void setDescuento_id(int descuento_id) {
		this.descuento_id = descuento_id;
	}

	public int getNr_descuento() {
		return nr_descuento;
	}

	public void setNr_descuento(int nr_descuento) {
		this.nr_descuento = nr_descuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

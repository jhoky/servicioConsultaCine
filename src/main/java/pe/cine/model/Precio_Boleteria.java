package pe.cine.model;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "precio_boleteria")
@ApiModel(description  = "Registro de los Precios de Boletería de Cada Cine")
public class Precio_Boleteria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int precio_boleteria_id;
	
	@Column(nullable=false)
	private Double precio;
	
	@ManyToOne
	@JoinColumn(name = "fk_dia_id",nullable = false,foreignKey = 
				@ForeignKey(foreignKeyDefinition = "foreign key (fk_dia_id) references dia (dia_id)"))
	private Dia dia;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_publico_id",nullable = false,foreignKey = 
				@ForeignKey(foreignKeyDefinition = "foreign key (fk_tipo_publico_id) references tipo_publico (tipo_publico_id)"))
	private Tipo_Publico tipo_publico;
	
	@ManyToOne
	@JoinColumn(name = "fk_tipo_sala_id",nullable = false,foreignKey = 
				@ForeignKey(foreignKeyDefinition = "foreign key (fk_tipo_sala_id) references tipo_sala (tipo_sala_id)"))
	private Tipo_Sala tipo_sala;
	
	@ManyToOne
	@JoinColumn(name = "fk_cine_id",nullable = false, foreignKey = 
				@ForeignKey(foreignKeyDefinition = "foreign key (fk_cine_id) references cine (cine_id)"))
	private Cine cine;
	
	public Precio_Boleteria() {
	}
	

	public Precio_Boleteria(Double precio, Dia dia, Tipo_Publico tipo_publico, Tipo_Sala tipo_sala, Cine cine) {
		super();
		this.precio = precio;
		this.dia = dia;
		this.tipo_publico = tipo_publico;
		this.tipo_sala = tipo_sala;
		this.cine = cine;
	}


	public int getPrecio_boleteria_id() {
		return precio_boleteria_id;
	}

	public void setPrecio_boleteria_id(int precio_boleteria_id) {
		this.precio_boleteria_id = precio_boleteria_id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public Tipo_Publico getTipo_publico() {
		return tipo_publico;
	}

	public void setTipo_publico(Tipo_Publico tipo_publico) {
		this.tipo_publico = tipo_publico;
	}

	public Tipo_Sala getTipo_sala() {
		return tipo_sala;
	}

	public void setTipo_sala(Tipo_Sala tipo_sala) {
		this.tipo_sala = tipo_sala;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}
	

}

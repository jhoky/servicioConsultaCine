package pe.cine.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "horario")
@ApiModel(description  = "Registro de los Horarios de las Películas de Cada Cine")
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int horario_id;
	
	@Column(nullable=false)
	private LocalDate fecha;
	
	@Column(length = 5, nullable=false)
	private String hora_inicio;
	
	@Column(nullable = false, columnDefinition = "boolean default 1")
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "fk_pelicula_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_pelicula_id) references pelicula (pelicula_id)"))
	private Pelicula pelicula;
	
	
	@ManyToOne
	@JoinColumn(name = "cine_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (cine_id) references cine (cine_id)"))
	private Cine cine;
	
	public Horario() {
	}


	public Horario(LocalDate fecha, String hora_inicio, boolean estado, Pelicula pelicula, Cine cine) {
		super();
		this.fecha = fecha;
		this.hora_inicio = hora_inicio;
		this.estado = estado;
		this.pelicula = pelicula;
		this.cine = cine;
	}


	public int getHorario_id() {
		return horario_id;
	}


	public void setHorario_id(int horario_id) {
		this.horario_id = horario_id;
	}
	

	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public String getHora_inicio() {
		return hora_inicio;
	}


	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}


	public Pelicula getPelicula() {
		return pelicula;
	}


	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}


	public Cine getCine() {
		return cine;
	}


	public void setCine(Cine cine) {
		this.cine = cine;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
		
	
	
	
}

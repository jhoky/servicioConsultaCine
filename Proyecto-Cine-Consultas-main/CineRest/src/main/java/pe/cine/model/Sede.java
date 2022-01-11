package pe.cine.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "sede")
@ApiModel(description  = "Registro de las Sedes de los Cines")
public class Sede implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int sede_id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String nombre;
	
	@Column(length = 300, nullable = false)
	private String direccion;
	
	@Column(length = 120, nullable = false)
	private String ubicacion;
	
	@Column
    private String imagenUrl;
	
	@Column(nullable = false)
	private int cantidad_salas;
	
	@Column(nullable = false, columnDefinition = "boolean default 1")
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name="fk_distrito_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_distrito_id) references distrito (distrito_id)"))
	private Distrito distrito;

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "sede_tipo_sala",
				joinColumns = @JoinColumn(name="sede_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (sede_id) references Sede (sede_id)")),
				inverseJoinColumns = @JoinColumn(name="tipo_sala_id", nullable = false,unique = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (tipo_sala_id) references tipo_sala (tipo_sala_id)")))
	private Set<Tipo_Sala> tipos_sala = new HashSet<>();
	
	public Sede() {
	}

	public Sede(String nombre, String direccion, String ubicacion, int cantidad_salas, boolean estado,
			Distrito distrito, String imagenUrl) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ubicacion = ubicacion;
		this.cantidad_salas = cantidad_salas;
		this.estado = estado;
		this.distrito = distrito;
		this.imagenUrl = imagenUrl;
	}


	public int getSede_id() {
		return sede_id;
	}

	public void setSede_id(int sede_id) {
		this.sede_id = sede_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCantidad_salas() {
		return cantidad_salas;
	}

	public void setCantidad_salas(int cantidad_salas) {
		this.cantidad_salas = cantidad_salas;
	}

	public Set<Tipo_Sala> getTipos_sala() {
		return tipos_sala;
	}

	public void setTipos_sala(Set<Tipo_Sala> tipos_sala) {
		this.tipos_sala = tipos_sala;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
}

package pe.cine.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "cine")
@ApiModel(description  = "Registro de las Cadenas de Cine")
public class Cine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int cine_id;
	
	@Column(length = 20, nullable=false, unique = true)
	private String nombre;
	
	@Column(nullable = false, columnDefinition = "boolean default 1")
	private boolean estado;

	@Column(nullable = false)
	private String imagen_url_cine;

	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "cine_sede",
			joinColumns = @JoinColumn(name="fk_cine_id",nullable = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = 
			"foreign key(fk_cine_id) references cine (cine_id)")),
			inverseJoinColumns = @JoinColumn(name ="fk_sede_id",nullable = false,
			foreignKey = @ForeignKey(foreignKeyDefinition = 
			"foreign key (fk_sede_id) references sede (fk_sede_id)")))
	private Set<Sede> lista_sedes = new HashSet<>();
	
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "cine_pelicula",
				joinColumns = @JoinColumn(name="fk_cine_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key(fk_cine_id) references cine (cine_id)")),
				inverseJoinColumns = @JoinColumn(name ="fk_pelicula_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_pelicula_id) references pelicula (pelicula_id)")))
	private Set<Pelicula> lista_peliculas = new HashSet<>();


	
	

	public Cine() {
		super();
	}


	public Cine(String nombre, boolean estado, String imagen_url_cine) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.imagen_url_cine = imagen_url_cine;
	}


	public int getCine_id() {
		return cine_id;
	}


	public void setCine_id(int cine_id) {
		this.cine_id = cine_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Sede> getLista_sedes() {
		return lista_sedes;
	}


	public void setLista_sedes(Set<Sede> lista_sedes) {
		this.lista_sedes = lista_sedes;
	}
	

	public Set<Pelicula> getLista_peliculas() {
		return lista_peliculas;
	}


	public void setLista_peliculas(Set<Pelicula> lista_peliculas) {
		this.lista_peliculas = lista_peliculas;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public String getImagen_url_cine() {
		return imagen_url_cine;
	}


	public void setImagen_url_cine(String imagen_url_cine) {
		this.imagen_url_cine = imagen_url_cine;
	}
	
	
	
	


}

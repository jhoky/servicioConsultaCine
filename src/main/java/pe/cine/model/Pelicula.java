package pe.cine.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pelicula")
@ApiModel(description  = "Registro de las Películas")
public class Pelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identificador único",required = true)
	private int pelicula_id;
	
	@Column(length = 80, nullable=false)
	private String nombre;
	
	@Column(length = 800, nullable=false)
	private String descripcion;
	
	@Column(length = 10, nullable=false)  
	private String duracion;
	
	@Column
    private String imagenUrl;
	
	@Column(nullable=false)
	private LocalDate fecha_estreno;
	
	@Column(nullable = false, columnDefinition = "boolean default 1")
	private boolean estado;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinTable(name = "pelicula_genero",
				joinColumns = @JoinColumn(name="fk_pelicula_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_pelicula_id) references pelicula (pelicula_id)")),
				inverseJoinColumns = @JoinColumn(name="fk_genero_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_genero_id) references genero (genero_id)")))
	private Set<Genero> lista_genero = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name = "fk_director_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_director_id) references director (director_id)"))
	private Director director;
	
	@ManyToOne
	@JoinColumn(name = "fk_clasificacion_id",nullable = false,
				foreignKey = @ForeignKey(foreignKeyDefinition = 
				"foreign key (fk_clasificacion_id) references clasificacion (clasificacion_id)"))
	private Clasificacion clasificacion;
	
	public Pelicula() {
	}

	public Pelicula(String nombre, String descripcion, String duracion, String imagenUrl, LocalDate fecha_estreno, boolean estado,
			Director director, Clasificacion clasificacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.imagenUrl = imagenUrl;
		this.fecha_estreno = fecha_estreno;
		this.estado = estado;
		this.director = director;
		this.clasificacion = clasificacion;
	}



	public int getPelicula_id() {
		return pelicula_id;
	}


	public void setPelicula_id(int pelicula_id) {
		this.pelicula_id = pelicula_id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public LocalDate getFecha_estreno() {
		return fecha_estreno;
	}


	public void setFecha_estreno(LocalDate fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}

	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Set<Genero> getLista_genero() {
		return lista_genero;
	}


	public void setLista_genero(Set<Genero> lista_genero) {
		this.lista_genero = lista_genero;
	}


	public Director getDirector() {
		return director;
	}


	public void setDirector(Director director) {
		this.director = director;
	}


	public Clasificacion getClasificacion() {
		return clasificacion;
	}


	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

}

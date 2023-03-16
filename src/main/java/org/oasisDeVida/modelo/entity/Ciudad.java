package org.oasisDeVida.modelo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@javax.persistence.Table(name = "ciudad")
public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length( min = 1, max = 10, message = "Min 1 y Max 50")
	@Column(name = "codigo", nullable = false)
	private String codigo;

	@NotBlank
	@Length( min = 3, max = 50, message = "Min 3 y Max 50")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "estado", nullable = true)
	private String estado;

//	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
//	private List<Granja> granjas;

	@ManyToOne(fetch = FetchType.LAZY)
	private Departamento departamento;




	public Ciudad() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


//
//	public List<Granja> getGranjas() {
//		return granjas;
//	}
//
//	public void setGranjas(List<Granja> granjas) {
//		this.granjas = granjas;
//	}

	@Override
	public String toString() {
		return "Ciudad [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado
				+ ", departamento=" + departamento + "]";
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Ciudad(Long id, @NotBlank @Length(min = 1, max = 10, message = "Min 1 y Max 50") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String nombre, String estado,
			Departamento departamento) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
		this.departamento = departamento;
	}






	

}

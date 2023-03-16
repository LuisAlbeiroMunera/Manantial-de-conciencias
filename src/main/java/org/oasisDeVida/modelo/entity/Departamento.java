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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@javax.persistence.Table(name = "departamento")
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	

	@NotBlank
	@Length(min = 1, max = 10, message = "min 1 y Max 10")
	@Column(name = "codigo",nullable = false)
	String codigo;
	
	@NotBlank
	@Length( min = 3, max = 50, message = "Min 3 y Max 50")
	@Column(name = "nombre",nullable = false)
	String nombre;
	
	@Column(name = "estado",nullable = true)
	private String estado;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Departamento() {
		super();
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", estado=" + estado
				+ "]";
	}
	public Departamento(Long id, @NotBlank @Length(min = 1, max = 10, message = "min 1 y Max 10") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String nombre, String estado,
			List<Ciudad> ciudades) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
	
	}

	
}

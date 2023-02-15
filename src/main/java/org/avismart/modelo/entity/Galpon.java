package org.avismart.modelo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@javax.persistence.Table(name = "galpon")
public class Galpon {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
@Length(max = 10, message = "Max 10")
@Column(name = "codigo",nullable = false)
private String codigo;

@NotBlank
@Length(min = 3, max = 50, message = "Min 3 y Max 50")
@Column(name = "descripcion",nullable = false)
private String descripcion;

@NotNull
@DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9")
@Column(name = "capacidad",nullable = false)
private String capacidad;

@Column(name = "estado",nullable = true)
private String estado;

@ManyToOne(fetch = FetchType.LAZY)
private Granja granja;

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
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getCapacidad() {
	return capacidad;
}
public void setCapacidad(String capacidad) {
	this.capacidad = capacidad;
}

public Galpon() {
	super();
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public Granja getGranja() {
	return granja;
}
public void setGranja(Granja granja) {
	this.granja = granja;
}
public Galpon(Long id, @NotBlank @Length(max = 10, message = "Max 10") String codigo,
		@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String descripcion,
		@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") String capacidad,
		String estado, Granja granja) {
	super();
	this.id = id;
	this.codigo = codigo;
	this.descripcion = descripcion;
	this.capacidad = capacidad;
	this.estado = estado;
	this.granja = granja;
}
@Override
public String toString() {
	return "Galpon [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", capacidad=" + capacidad
			+ ", estado=" + estado + ", granja=" + granja + "]";
}


}

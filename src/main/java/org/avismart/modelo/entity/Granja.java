package org.avismart.modelo.entity;

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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
@Entity
@javax.persistence.Table(name = "granja")
public class Granja implements Serializable{
	
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
@Length( min = 1, max = 10, message = "Min 1 y Max 50")
@Column(name = "codigo",nullable = false)
private String codigo;

@NotBlank
@Length( min = 3, max = 10, message = "Min 3 y Max 50")
@Column(name = "descripcion",nullable = false)
private String descripcion;

@Column(name = "estado",nullable = true)
private String estado;

//@OneToMany(mappedBy = "granja", fetch = FetchType.LAZY)
//private List<Galpon> galpones;

@ManyToOne(fetch = FetchType.LAZY)
private Ciudad ciudad;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String granja() {
	return codigo;
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

public Ciudad getCiudad() {
	return ciudad;
}
public void setCiudad(Ciudad ciudad) {
	this.ciudad = ciudad;
}

public Granja() {
	
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public Granja(Long id, @NotBlank @Length(min = 1, max = 10, message = "Min 1 y Max 50") String codigo,
		@NotBlank @Length(min = 3, max = 10, message = "Min 3 y Max 50") String descripcion, String estado,
		Ciudad ciudad) {
	super();
	this.id = id;
	this.codigo = codigo;
	this.descripcion = descripcion;
	this.estado = estado;
	this.ciudad = ciudad;
}
@Override
public String toString() {
	return "Granja [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
			+ ", ciudad=" + ciudad + "]";
}



}


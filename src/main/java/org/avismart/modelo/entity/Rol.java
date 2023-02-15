package org.avismart.modelo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "ROL")

public class Rol implements Serializable{
	

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Validar!")
	@Length(max = 12,min = 1, message = "Min 1 y Max 12")
	@Column(name = "codigo",nullable = false)	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	@NotBlank(message = "Validar!")
	@Length(max = 12,min = 4, message = "Min 4 y Max 12")
	@Column(name = "DSROL_NOMBRE", columnDefinition = "varchar(60) not null COMMENT 'Descripcion o nombre del rol'")
	private String nombreRol;
	
	@Column(name = "estado",nullable = true)
	private String estado;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "RECURSOS_ROL", joinColumns = @JoinColumn(name = "ROL_ID"),
	inverseJoinColumns = @JoinColumn(name = "RECURSO_ID"))
	private List<RecursosRol> listaPrivilegiosRol;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombreRol() {
		return nombreRol;
	}


	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}


	public List<RecursosRol> getListaPrivilegiosRol() {
		return listaPrivilegiosRol;
	}

	public Rol() {
		super();
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public void setListaPrivilegiosRol(List<RecursosRol> listaPrivilegiosRol) {
		this.listaPrivilegiosRol = listaPrivilegiosRol;
	}


	public Rol(Long id,
			@NotBlank(message = "Validar!") @Length(max = 12, min = 1, message = "Min 1 y Max 12") String codigo,
			@NotBlank(message = "Validar!") @Length(max = 12, min = 4, message = "Min 4 y Max 12") String nombreRol,
			String estado, List<RecursosRol> listaPrivilegiosRol) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombreRol = nombreRol;
		this.estado = estado;
		this.listaPrivilegiosRol = listaPrivilegiosRol;
	}


	@Override
	public String toString() {
		return "Rol [id=" + id + ", codigo=" + codigo + ", nombreRol=" + nombreRol + ", estado=" + estado
				+ ", listaPrivilegiosRol=" + listaPrivilegiosRol + "]";
	}


	
}

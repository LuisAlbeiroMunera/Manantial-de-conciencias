package org.oasisDeVida.modelo.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "Validar!")
	@Length(max = 12,min = 1, message = "Min 1 y Max 12")
	@Column(name = "codigo",nullable = false)	
	private String codigo;
	
	@NotBlank
	@Length(min = 3, max = 50, message = "Min 3 y Max 50")
	@Column(name = "descripcion",nullable = false)
	private String descripcion;
	
	@Column(name = "estado",nullable = true)
	private String estado;
	

	private Bebedero bebedero;
	
	@OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RegistroArduino> registros;
	
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
	
	public Bebedero getBebedero() {
		return bebedero;
	}
	public void setBebedero(Bebedero bebedero) {
		this.bebedero = bebedero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<RegistroArduino> getRegistros() {
		return registros;
	}
	public void setRegistros(List<RegistroArduino> registros) {
		this.registros = registros;
	}
	public Sensor() {
		super();
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Sensor(Long id,
			@NotBlank(message = "Validar!") @Length(max = 12, min = 1, message = "Min 1 y Max 12") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String descripcion, String estado,
			Bebedero bebedero, List<RegistroArduino> registros) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.bebedero = bebedero;
		this.registros = registros;
	}
	@Override
	public String toString() {
		return "Sensor [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", bebedero=" + bebedero + ", registros=" + registros + "]";
	}


	
}

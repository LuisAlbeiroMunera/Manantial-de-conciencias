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
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@javax.persistence.Table(name = "bebedero")
public class Bebedero implements Serializable {

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
	@Column(name = "ubicacion", nullable = false)
	private String ubicacion;
	
	@Column(name = "estado", nullable = true)
	private String estado;


	@ManyToOne(fetch = FetchType.LAZY)
	private Galpon galpon;


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


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Galpon getGalpon() {
		return galpon;
	}


	public void setGalpon(Galpon galpon) {
		this.galpon = galpon;
	}


	public Bebedero(Long id, @NotBlank @Length(min = 1, max = 10, message = "Min 1 y Max 50") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String ubicacion, String estado,
			Galpon galpon) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.ubicacion = ubicacion;
		this.estado = estado;
		this.galpon = galpon;
	}


	public Bebedero() {
		super();
	}


	@Override
	public String toString() {
		return "Bebedero [id=" + id + ", codigo=" + codigo + ", ubicacion=" + ubicacion + ", estado=" + estado
				+ ", galpon=" + galpon + "]";
	}



	

}

package org.oasisDeVida.modelo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@javax.persistence.Table(name = "lote")
public class Lote implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	

	@Column(name = "estado",nullable = true)
	private String estado;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_entrada",nullable = true)
	private Date fecha_entrada;
	
	
	@DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9")
	@DecimalMin(value = "1", inclusive = true, message = "Min 1")
	@Column(name = "cantidad",nullable = false)
	private Double cantidad;
	
	
	@DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9")
	@DecimalMin(value = "0.1", inclusive = true, message = "Min 1")
	@Column(name = "costo_unitario",nullable = false)
	private Double costo_unitario;
	
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
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
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getCosto_unitario() {
		return costo_unitario;
	}
	public void setCosto_unitario(Double costo_unitario) {
		this.costo_unitario = costo_unitario;
	}
	public Lote() {
		super();
	}
	public Lote(Long id, @NotBlank @Length(max = 10, message = "Max 10") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String descripcion, String estado,
			@NotNull Date fecha_entrada,
			@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double cantidad,
			@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "0.1", inclusive = true, message = "Min 1") Double costo_unitario) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha_entrada = fecha_entrada;
		this.cantidad = cantidad;
		this.costo_unitario = costo_unitario;
	}
	@Override
	public String toString() {
		return "Lote [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado
				+ ", fecha_entrada=" + fecha_entrada + ", cantidad=" + cantidad + ", costo_unitario=" + costo_unitario
				+ "]";
	}

	
}

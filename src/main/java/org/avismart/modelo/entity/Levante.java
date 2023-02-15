package org.avismart.modelo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@javax.persistence.Table(name = "levate")
public class Levante implements Serializable{

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
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_entrada",nullable = false)
	private Date fecha_entrada;
	

	@DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9")
	@DecimalMin(value = "1", inclusive = true, message = "Min 1")
	@Column(name = "cantidad_entrada",nullable = false)
	private Double cantidad_entrada;
	

	@DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9")
	@DecimalMin(value = "1", inclusive = true, message = "Min 1")
	@Column(name = "cantidad_salida",nullable = true)
	private @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double cantidad_salida;
	
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "fecha_salida",nullable = true)
	private Date fecha_salida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Galpon galpon;

	@ManyToOne(fetch = FetchType.LAZY)
	private Lote lote;

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

	public Double getCantidad_entrada() {
		return cantidad_entrada;
	}

	public void setCantidad_entrada(Double cantidad_entrada) {
		this.cantidad_entrada = cantidad_entrada;
	}

	public @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double getCantidad_salida() {
		return cantidad_salida;
	}

	public void setCantidad_salida(@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") @NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double cantidad_salida) {
		this.cantidad_salida = cantidad_salida;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public Galpon getGalpon() {
		return galpon;
	}

	public void setGalpon(Galpon galpon) {
		this.galpon = galpon;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Levante [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", fecha_entrada="
				+ fecha_entrada + ", cantidad_entrada=" + cantidad_entrada + ", cantidad_salida=" + cantidad_salida
				+ ", fecha_salida=" + fecha_salida + ", galpon=" + galpon + ", lote=" + lote + ", estado=" + estado
				+ "]";
	}

	public Levante(Long id, @NotBlank @Length(max = 10, message = "Max 10") String codigo,
			@NotBlank @Length(min = 3, max = 50, message = "Min 3 y Max 50") String descripcion,
			@NotNull Date fecha_entrada,
			@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double cantidad_entrada,
			@NotNull @DecimalMax(value = "999999.9", inclusive = true, message = "Max 999999.9") @DecimalMin(value = "1", inclusive = true, message = "Min 1") Double cantidad_salida,
			@NotNull Date fecha_salida, Galpon galpon, Lote lote, String estado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.fecha_entrada = fecha_entrada;
		this.cantidad_entrada = cantidad_entrada;
		this.cantidad_salida = cantidad_salida;
		this.fecha_salida = fecha_salida;
		this.galpon = galpon;
		this.lote = lote;
		this.estado = estado;
	}

	public Levante() {
		super();
	}


		
	
}

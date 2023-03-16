package org.oasisDeVida.modelo.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "registro_arduino")
public class RegistroArduino implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Validar!")
	@Length(max = 12,min = 1, message = "Min 1 y Max 12")
	@Column(name = "codigo",nullable = false)	
	private String codigo;
	
	@NotNull
	@Column(name = "estado",nullable = true)
	private String estado;
	
	@Column(name = "fecha",nullable = false)
	private Date fecha;
	
	@Column(name = "reg_flujo_agua",nullable = true)
	private String regFlujoAgua;
	
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	private Sensor sensor;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getRegFlujoAgua() {
		return regFlujoAgua;
	}
	public void setRegFlujoAgua(String regFlujoAgua) {
		this.regFlujoAgua = regFlujoAgua;
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
	
	
	public Sensor getSensores() {
		return sensor;
	}
	public void setSensores(Sensor sensor) {
		this.sensor= sensor;
	}
	public RegistroArduino() {
		super();
	}
	public RegistroArduino(Long id,
			@NotBlank(message = "Validar!") @Length(max = 12, min = 1, message = "Min 1 y Max 12") String codigo,
			@NotNull String estado, Date fecha, String regFlujoAgua, Sensor sensor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.estado = estado;
		this.fecha = fecha;
		this.regFlujoAgua = regFlujoAgua;
		this.sensor = sensor;
	}
	@Override
	public String toString() {
		return "RegistroArduino [id=" + id + ", codigo=" + codigo + ", estado=" + estado + ", fecha=" + fecha
				+ ", regFlujoAgua=" + regFlujoAgua + ", sensor=" + sensor + "]";
	}
	

	
}

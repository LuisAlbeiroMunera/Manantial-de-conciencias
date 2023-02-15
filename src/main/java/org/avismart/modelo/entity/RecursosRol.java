package org.avismart.modelo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "RECURSOS")

public class RecursosRol  implements Serializable{


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "DSRECURSO", nullable = false, length = 150)
	private String recurso;
	
	@Column(name = "NJERARQUIA", nullable = true, length = 5)
	private String jerarquia;
	
	@Column(name = "DSMENU", nullable = true, length = 10)
	private String menu;
	
	@Column(name = "DSNOMBRE_MENU", nullable = true, length = 50)
	private String nombreMenu;
	
	@Column(name = "NPOSICION", nullable = true, length = 5)
	private String posicion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public RecursosRol(Long id, String recurso, String jerarquia, String menu, String nombreMenu, String posicion) {
		super();
		this.id = id;
		this.recurso = recurso;
		this.jerarquia = jerarquia;
		this.menu = menu;
		this.nombreMenu = nombreMenu;
		this.posicion = posicion;
	}

	public RecursosRol() {
		super();
	}

	@Override
	public String toString() {
		return "RecursosRol [id=" + id + ", recurso=" + recurso + ", jerarquia=" + jerarquia + ", menu=" + menu
				+ ", nombreMenu=" + nombreMenu + ", posicion=" + posicion + "]";
	}

	
	
	

}

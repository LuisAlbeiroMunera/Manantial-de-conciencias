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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(name = "USUARIOS")

public class Usuario implements Serializable {
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Validar!")
	@Length(max = 12,min = 4, message = "Min 4 y Max 12")
	@Column(name = "DSNOMBRE", nullable = false, length = 60)
	private String nombreUsuario;
	
	@NotEmpty(message = "validar!")
	@Email(message = "Formato invalido")
	@Column(name = "DSEMAIL", nullable = false, length = 60)
	private String email;	
	
	@NotBlank(message = "Validar!")
	@Column(name = "DSPASSWORD", nullable = false, length = 255)
	private String password;
	
	@NotBlank(message = "Validar!")
	@Length(min = 4, max = 20, message = "Min 4 y Max 20")
	@Column(name = "DSUSER_NAME", nullable = false, length = 60)
	private String userName;
	

	@NotBlank(message = "Validar!")
	@Length(min = 7, max = 12, message = "Min 7 y Max 12")
	@Column(name = "NTELEFONO",  nullable = false, length = 60)
	private String telefono;
	
	@Column(name = "DSCLAVE_TEMPORAL", nullable = true, length = 3)
	private String claveTemporal;
	

	@Column(nullable = true)
	private String estado;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USUARIO_ROL", joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private List<Rol> listaRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getClaveTemporal() {
		return claveTemporal;
	}

	public void setClaveTemporal(String claveTemporal) {
		this.claveTemporal = claveTemporal;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Usuario(Long id,
			@NotBlank(message = "Validar!") @Length(max = 12, min = 4, message = "Min 4 y Max 12") String nombreUsuario,
			@NotEmpty(message = "validar!") @Email(message = "Formato invalido") String email,
			@NotBlank(message = "Validar!") @Length(min = 4, max = 8, message = "Min 4 y Max 8") String password,
			@NotBlank(message = "Validar!") @Length(min = 4, max = 20, message = "Min 4 y Max 20") String userName,
			@NotBlank(message = "Validar!") @Length(min = 7, max = 12, message = "Min 7 y Max 12") String telefono,
			String claveTemporal, @NotBlank String estado, List<Rol> listaRoles) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.telefono = telefono;
		this.claveTemporal = claveTemporal;
		this.estado = estado;
		this.listaRoles = listaRoles;
	}

	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", email=" + email + ", password=" + password
				+ ", userName=" + userName + ", telefono=" + telefono + ", claveTemporal=" + claveTemporal + ", estado="
				+ estado + ", listaRoles=" + listaRoles + "]";
	}



}

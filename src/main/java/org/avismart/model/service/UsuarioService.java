package org.avismart.model.service;

import java.util.List;


import org.avismart.modelo.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioService {
	public List<Usuario> listarUsuarios();
	
	public Usuario consultarUsuario(Long id);
	
	public void agregarUsuario(Usuario usuario);
	
	public void eliminarUsuario(Long id);
	
	public Usuario consultarPorIdentificacion(Usuario usuario);
	public List<Usuario>  consultarUsuarios(Usuario usuario);
	
	
}

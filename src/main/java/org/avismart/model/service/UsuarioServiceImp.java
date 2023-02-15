package org.avismart.model.service;

import java.util.ArrayList;
import java.util.List;

import org.avismart.modelo.dao.UsuarioDAO;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioServiceImp implements UsuarioService{
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		
		return usuarioDAO.findAll();
//		return new ArrayList<Usuario>();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario consultarUsuario(Long id) {		
		return usuarioDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void agregarUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
		
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		usuarioDAO.deleteById(id); 
		
		
	}

	@Override
	public Usuario consultarPorIdentificacion(Usuario usuario) {
		
		return null;
	}

	@Override
	public List<Usuario>  consultarUsuarios(Usuario usuario) {
//		return usuarioDAO.consultarUsuarios(usuario.getId(), usuario.getUserName(), usuario.getNombreUsuario());
		return new ArrayList<Usuario>();
	}


}

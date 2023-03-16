package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Lote;
import org.oasisDeVida.modelo.entity.Rol;
import org.springframework.stereotype.Repository;
@Repository
public interface RolService {
	public List<Rol> listarRoles();
	
	public Rol consultarRol(Long id);
	
	public void agregarRol(Rol rol);
	public void inactivarRol(Long id);
}

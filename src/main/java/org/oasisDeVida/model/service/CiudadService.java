package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Ciudad;
import org.oasisDeVida.modelo.entity.Departamento;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadService {
	public List<Ciudad> listarCiudades();
	
	public Ciudad consultarCiudad(Long id);
	
	public void agregarCiudad(Ciudad ciudad);
	
	public void inactivarCiudad(Long id);
	
	public Departamento consultarPorIdentificacion(Ciudad ciudad);

}

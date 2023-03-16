package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Departamento;
import org.oasisDeVida.modelo.entity.Galpon;
import org.oasisDeVida.modelo.entity.Granja;
import org.springframework.stereotype.Repository;

@Repository
public interface GalponService {
	public List<Galpon> listarGalpones();
	
	public Galpon consultarGalpon(Long id);
	
	public void agregarGalpon(Galpon galpon);
	
	public void inactivarGalpon(Long id);
	
	public Granja consultarPorIdentificacion(Galpon galpon);

}

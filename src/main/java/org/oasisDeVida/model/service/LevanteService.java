package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Levante;
import org.springframework.stereotype.Repository;
@Repository
public interface LevanteService {
	public List<Levante> listarLevantes();
	
	public Levante consultarLevante(Long id);
	
	public void agregarLevante(Levante levante);
	
	public void inactivarLevante(Long id);
	
	public Levante consultarPorIdentificacion(Levante levante);
	public List<Levante>  consultarLevantes(Levante levante);
}

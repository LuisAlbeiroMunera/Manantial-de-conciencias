package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Departamento;
import org.oasisDeVida.modelo.entity.Granja;
import org.springframework.stereotype.Repository;
@Repository
public interface GranjaService {
	public List<Granja> listarGranjas();
	
	public Granja consultarGranja(Long id);
	
	public void agregarGranja(Granja granja);
	
	public void inactivarGranja(Long id);
	
	public Granja consultarPorIdentificacion(Granja granja);

}

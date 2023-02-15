package org.avismart.model.service;

import java.util.List;
import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Galpon;
import org.avismart.modelo.entity.Granja;
import org.springframework.stereotype.Repository;

@Repository
public interface GalponService {
	public List<Galpon> listarGalpones();
	
	public Galpon consultarGalpon(Long id);
	
	public void agregarGalpon(Galpon galpon);
	
	public void inactivarGalpon(Long id);
	
	public Granja consultarPorIdentificacion(Galpon galpon);

}

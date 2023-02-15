package org.avismart.model.service;

import java.util.List;
import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Granja;
import org.springframework.stereotype.Repository;
@Repository
public interface GranjaService {
	public List<Granja> listarGranjas();
	
	public Granja consultarGranja(Long id);
	
	public void agregarGranja(Granja granja);
	
	public void inactivarGranja(Long id);
	
	public Granja consultarPorIdentificacion(Granja granja);

}

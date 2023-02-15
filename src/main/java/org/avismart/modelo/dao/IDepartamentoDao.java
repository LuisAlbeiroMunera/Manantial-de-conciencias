 package org.avismart.modelo.dao;



import java.util.List;

import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Galpon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IDepartamentoDao extends JpaRepository<Departamento, Long> {
	Departamento findByCodigo(String codigo);
	List<Departamento> findByCodigoContainingAndNombreContainingAndEstado(String codigo ,String nombre,String estado);
	List<Departamento> findByEstado(String estado);	
}


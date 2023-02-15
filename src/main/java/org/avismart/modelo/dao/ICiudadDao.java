package org.avismart.modelo.dao;




import java.util.List;

import org.avismart.modelo.entity.Ciudad;
import org.avismart.modelo.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICiudadDao extends JpaRepository<Ciudad, Long>{
Ciudad findByCodigo(String codigo);
List<Ciudad> findByCodigoContainingAndNombreContainingAndEstadoAndDepartamento(String codigo, String nombre, String estado, Departamento departamento);
List<Ciudad> findByCodigoContainingAndNombreContainingAndEstado(String codigo, String nombre, String estado);
List<Ciudad> findByEstado(String estado);

}

package org.avismart.modelo.dao;



import java.util.Date;
import java.util.List;

import org.avismart.modelo.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoteDAO extends JpaRepository<Lote, Long>{

List<Lote> findByCodigoContainingAndDescripcionContainingAndEstado(String codigo, String descripcion, String estado);
Lote findByCodigo(String codigo);
List<Lote>findByEstado(String estado);
}

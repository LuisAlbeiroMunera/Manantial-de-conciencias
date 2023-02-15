package org.avismart.modelo.dao;





import java.util.List;

import org.avismart.modelo.entity.Bebedero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IBebederoDAO extends JpaRepository<Bebedero, Long>{
Bebedero findByCodigo(String codigo);
List<Bebedero> findByEstado(String estado);

}

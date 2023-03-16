package org.oasisDeVida.modelo.dao;



import java.util.List;

import org.oasisDeVida.modelo.entity.Galpon;
import org.oasisDeVida.modelo.entity.Levante;
import org.oasisDeVida.modelo.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LevanteDAO extends JpaRepository<Levante, Long>{
	List<Levante> findByCodigoContainingAndDescripcionContainingAndEstado(String codigo, String descripcion, String estado);
	List<Levante> findByCodigoContainingAndDescripcionContainingAndEstadoAndGalpon(String codigo, String descripcion, String estado, Galpon galpon);
	List<Levante> findByCodigoContainingAndDescripcionContainingAndEstadoAndLote(String codigo, String descripcion, String estado, Lote lote);
	List<Levante> findByCodigoContainingAndDescripcionContainingAndEstadoAndLoteAndGalpon(String codigo, String descripcion, String estado, Lote lote, Galpon galpon);
	Levante findByCodigo(String codigo);
}

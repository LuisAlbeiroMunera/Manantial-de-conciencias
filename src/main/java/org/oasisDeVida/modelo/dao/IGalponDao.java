package org.oasisDeVida.modelo.dao;

import java.util.List;

import org.oasisDeVida.modelo.entity.Galpon;
import org.oasisDeVida.modelo.entity.Granja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IGalponDao extends JpaRepository<Galpon, Long> {
	List<Galpon> findByCodigoContainingAndDescripcionContainingAndEstadoAndGranjaContaining(String codigo ,String descripcion,String estado, Granja granja);

	List<Galpon> findByCodigoContainingAndDescripcionContainingAndEstado(String codigo ,String descripcion,String estado);
	Galpon findByCodigo(String codigo);
	List<Galpon>findByEstado(String estado);
}

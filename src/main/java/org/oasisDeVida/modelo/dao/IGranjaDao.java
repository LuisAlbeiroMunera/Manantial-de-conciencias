package org.oasisDeVida.modelo.dao;


import java.util.List;

import org.oasisDeVida.modelo.entity.Ciudad;
import org.oasisDeVida.modelo.entity.Granja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGranjaDao extends JpaRepository<Granja, Long> {


	List<Granja> findByCodigoContainingAndDescripcionContainingAndEstado(String codigo ,String descripcion,String estado);
	List<Granja> findByCodigoContainingAndDescripcionContainingAndEstadoAndCiudad(String codigo ,String descripcion,String estado, Ciudad ciudad);
	Granja findByCodigo(String codigo);
	List<Granja> findByEstado(String estado);
}
package org.oasisDeVida.modelo.dao;

import java.util.List;

import org.oasisDeVida.modelo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	List<Rol> findByCodigoContainingAndNombreRolContainingAndEstado(String codigo, String nombreRol, String estado);
	Rol findByCodigo(String codigo);
	Rol findByNombreRol(String nombreRol);
	List<Rol> findByEstado(String estado);
}
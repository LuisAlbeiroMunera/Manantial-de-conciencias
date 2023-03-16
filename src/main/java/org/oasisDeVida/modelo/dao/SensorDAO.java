package org.oasisDeVida.modelo.dao;



import java.util.List;

import org.oasisDeVida.modelo.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SensorDAO extends JpaRepository<Sensor, Long>{

	List<Sensor> findByCodigoContainingAndDescripcionContainingAndEstado(String codigo, String descripcion, String estado);
	List<Sensor> findByEstado(String estado);
}

package org.oasisDeVida.modelo.dao;



import org.oasisDeVida.modelo.entity.RegistroArduino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RegistroArduinoDAO extends JpaRepository<RegistroArduino, Long>{



}

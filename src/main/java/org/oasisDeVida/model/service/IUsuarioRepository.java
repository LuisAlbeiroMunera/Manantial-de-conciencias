package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	Usuario findByUserName(String userName);
	
	boolean existsByUserName(String userName);
	
	boolean existsByEmail(String email);
	
	List<Usuario> findAll();
	
	@Query(value = "SELECT (DATEDIFF(NOW(),DATE_ADD(actividad.fechaCorte, INTERVAL cliente.plazo DAY))) > 0  AS faltaPago FROM TPCP_CLIENTE AS cliente INNER JOIN TPCP_ACTIVIDAD  AS actividad ON (cliente.Id=actividad.idCliente) WHERE corresponsalWeb = ?1 AND estadoPago=0 limit 1 ", nativeQuery = true)
	int existePago(String codigo);

}
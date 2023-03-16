package org.oasisDeVida.modelo.dao;



import java.util.ArrayList;
import java.util.List;

import org.oasisDeVida.modelo.entity.Rol;
import org.oasisDeVida.modelo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long>{
//@Query("SELECT u FROM Usuario u, USUARIO_ROL ur,  ROL r WHERE 1=1 AND u.id = ur.usuario_id AND r.id = ur.rol_id AND (:id IS NULL OR u.id = :id) AND (:usuario ='' OR u.usuario = :usuario)  AND (:nombre = '' OR u.nombre = :nombre)")
//	@Query("SELECT u FROM USUARIOS u WHERE 1=1 AND (u.dsuser_name = :userName)")
//	List<Usuario> consultarUsuarios(@Param("userName")String userName);	
	
	 /**
	 * @param usuario
	 * @return
	 */

	List<Usuario> findByUserNameContaining(String identificacion);
	List<Usuario> findByNombreUsuarioContainingIgnoreCase(String nombre);
	List<Usuario> findByNombreUsuarioContainingAndUserNameContainingAndEstadoAndListaRoles(String nombre ,String userName,String estado, List<Rol> list);
	List<Usuario> findByNombreUsuarioContainingAndUserNameContainingAndEstado(String nombre ,String userName,String estado);
	
	Usuario findByUserNameAndEstado(String identificacion, String estado);
	 Usuario findByUserName(String identificacion);
	 Usuario findByEmail(String email);
	 Long findById(String id);
}

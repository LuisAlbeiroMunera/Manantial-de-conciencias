package reportes;

import java.util.ArrayList;
import java.util.List;

import org.oasisDeVida.modelo.entity.Usuario;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class UsuarioDataSource implements JRDataSource {
	
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	private int usuarioActual = -1;
	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return ++usuarioActual < listaUsuarios.size();
	}
	
	public void addUsuario(Usuario usuario) {		
		this.listaUsuarios.add(usuario);
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object valor = null;
		
		if ("email".equals(jrField.getName())) {
			valor = listaUsuarios.get(usuarioActual).getEmail();
		} else if("nombreUsuario".equals(jrField.getName())){
			valor = listaUsuarios.get(usuarioActual).getNombreUsuario();
		}else if("telefono".equals(jrField.getName())){
			valor = listaUsuarios.get(usuarioActual).getTelefono();
		}
		return valor;
	}

}

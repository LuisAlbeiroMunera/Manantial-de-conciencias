package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.RolRepository;
import org.avismart.modelo.dao.UsuarioDAO;
import org.avismart.modelo.entity.Rol;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private UsuarioDAO usuarioService;

	@GetMapping("/formularioUsuario")
	public String registrarUsuario(Model modelo) {

		modelo.addAttribute("titulo", "Registro de usuario");
		modelo.addAttribute("accion", "registrar");
		Usuario usuario = new Usuario();
		usuario.setId(0L);
		usuario.setEstado("1");
		usuario.setClaveTemporal("N");
		List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", usuario);
		return "usuario/registrar_usuario";

	}

	@PostMapping("/registrarUsuario")
	public String usuarioRegistrado(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
		
		System.out.println(usuario);

		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de usuarios");
			modelo.addAttribute("accion", "Registrar");
			usuario.setClaveTemporal("N");
			usuario.setEstado("1");
			List<Rol> roles = rolRepository.findByEstado("1");
			modelo.addAttribute("roles", roles);
			modelo.addAttribute("usuario", usuario);
			return "usuario/registrar_usuario";
		}
		Usuario usuarioident = usuarioService.findByUserName(usuario.getUserName());
		Usuario usuarioemail = usuarioService.findByEmail(usuario.getEmail());
		if (usuarioident != null && usuarioemail != null) {
			modelo.addAttribute("accion", "Registrar");
			usuario.setClaveTemporal("N");
			usuario.setEstado("1");
			List<Rol> roles = rolRepository.findByEstado("1");
			modelo.addAttribute("roles", roles);
			
			modelo.addAttribute("error", "La identificacion Y el correo del usuario ya se encuentran registradas!");
		} else 
			if (usuarioident != null) {
				modelo.addAttribute("accion", "Registrar");
				usuario.setClaveTemporal("N");
				usuario.setEstado("1");
				List<Rol> roles = rolRepository.findByEstado("1");
				modelo.addAttribute("roles", roles);
				modelo.addAttribute("error", "La identificacion del usuario ya se encuentra registrada!");
			} else 
				if (usuarioemail != null) {
					modelo.addAttribute("accion", "Registrar");
					usuario.setClaveTemporal("N");
					usuario.setEstado("1");
					List<Rol> roles = rolRepository.findByEstado("1");
					modelo.addAttribute("roles", roles);
					modelo.addAttribute("error", "El correo del usuario ya se encuentra registrado!");
				} else {
					List<Rol> roles = rolRepository.findByEstado("1");
					modelo.addAttribute("roles", roles);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(hashedPassword);		
			usuario.setClaveTemporal("N");
			usuario.setEstado("1");
			usuarioService.save(usuario);
			flash.addFlashAttribute("info", "Usuario registrado con exito");
			status.setComplete();
			return "redirect:/listarUsuarios";
		}
		
		flash.addFlashAttribute("error", "El usuario ya se encuentra registrado");
		return "usuario/registrar_usuario";
		
//		return "redirect:/registrarUsuario";
	}

	@GetMapping("/listarUsuarios")
	public String listaUsuarios(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("accion", "Filtrar");
		List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("titulo", "Listado de usuarios");
		return "usuario/listar_usuarios";

	}

	@GetMapping("/principal")
	public String paginaPrincipal(Model modelo) {
		return "usuario/landing";

	}

	@PostMapping("/filtrarUsuario")
	public String filtrar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Usuario> filtro = null;
	if (usuario.getListaRoles().isEmpty()) {
		filtro= usuarioService.findByNombreUsuarioContainingAndUserNameContainingAndEstado(usuario.getNombreUsuario(), usuario.getUserName(),usuario.getEstado());
		
		
	}else {
		filtro= usuarioService.findByNombreUsuarioContainingAndUserNameContainingAndEstadoAndListaRoles(usuario.getNombreUsuario(), usuario.getUserName(),usuario.getEstado(),usuario.getListaRoles());
	
	}
	List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("titulo", "Listado de usuarios");
		modelo.addAttribute("usuarios", filtro);
		return "usuario/listar_usuarios";

	}

	@PostMapping("/consultarUsuario")
	public String consultarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores,
			Model modelo, SessionStatus status) {

		if (usuario.getId() > 0) {
			usuario = usuarioService.findById(usuario.getId()).orElse(null);
			if (usuario == null) {
				return "redirect:/listar_usuarios";
			}
		} else {
			return "redirect:/listar_usuarios";

		}
		List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del usuario # " + usuario.getId());
		modelo.addAttribute("usuario", usuario);
		return "usuario/consultar_usuario";

	}
	@PostMapping("/modificandoUsuario")
	public String modificarUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {

		modelo.addAttribute("titulo", "Modificando usuario");
		modelo.addAttribute("accion", "modificar");
		Usuario nuevoUsuario = usuarioService.findById(usuario.getId()).orElse(usuario);
		List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", nuevoUsuario);
		return "usuario/modificar_usuario";

	}
	
	@PostMapping("/modificarUsuario")
	public String modificaUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
		modelo.addAttribute("titulo", "Modificando usuario");
		modelo.addAttribute("accion", "modificar");
		Usuario nuevoUsuario = usuarioService.findById(usuario.getId()).orElse(usuario);
		List<Rol> roles = rolRepository.findByEstado("1");
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", nuevoUsuario);

		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "modoficar usuario");
			modelo.addAttribute("accion", "modificar");
			return "usuario/modificar_usuario";
		}
//		Usuario userAnterior = usuarioService.findById(usuario.getId()).orElse(null);
//		
//		if (userAnterior.getUserName() != usuario.getUserName() && userAnterior.getEmail() != usuario.getEmail()) {
//			Usuario usuarioident = usuarioService.findByUserName(usuario.getUserName());
//			Usuario usuarioemail = usuarioService.findByEmail(usuario.getEmail());
//			if (usuarioident != null && usuarioemail != null) {
//				
//				modelo.addAttribute("error", "La identificacion Y el correo del usuario ya se encuentran registradas!");
//			}else 
//				if (usuarioident != null) {
//					
//					modelo.addAttribute("error", "La identificacion del usuario ya se encuentra registrada!");
//				} else 
//					if (usuarioemail != null) {
//						
//						modelo.addAttribute("error", "El correo del usuario ya se encuentra registrado!");
//					}
//				
//		} else
//			if (userAnterior.getUserName() != usuario.getUserName()) {
//				Usuario usuarioident = usuarioService.findByUserName(usuario.getUserName());
//				if (usuarioident != null) {
//					
//					modelo.addAttribute("error", "La identificacion del usuario ya se encuentra registrada!");
//				} 
//			}else 
//				if (userAnterior.getEmail() != usuario.getEmail()) {
//					Usuario usuarioemail = usuarioService.findByEmail(usuario.getEmail());
//					if (usuarioemail != null) {
//						
//						modelo.addAttribute("error", "El correo del usuario ya se encuentra registrado!");
//					}
//				
//			} else {
//			
				modelo.addAttribute("roles", roles);
				usuarioService.save(usuario);
				flash.addFlashAttribute("info", "Usuario modificado con exito");
				status.setComplete();
				return "redirect:/listarUsuarios";
//			}
//		flash.addFlashAttribute("error", "El usuario ya se encuentra registrado");
//		return "usuario/modificar_usuario";
//		
//		return "redirect:/registrarUsuario";
	}

	@PostMapping("/inactivarUsuario")
	public String inactivarUsuario(@ModelAttribute("usuario") Usuario usuario, Model modelo, RedirectAttributes flash) {
		
		if (usuario.getId() > 0) {
			usuario = usuarioService.findById(usuario.getId()).orElse(usuario);
			usuario.setEstado("0");
			flash.addFlashAttribute("info", "Usuario inactivado con exito");

			usuarioService.save(usuario);
		
		}

		return "redirect:/listarUsuarios";

	}
	@PostMapping("/activarUsuario")
	public String activarUsuario(@ModelAttribute("usuario") Usuario usuario, Model modelo, RedirectAttributes flash) {
		
		if (usuario.getId() > 0) {
			usuario = usuarioService.findById(usuario.getId()).orElse(usuario);
			usuario.setEstado("1");
			flash.addFlashAttribute("info", "Usuario activado con exito");

			usuarioService.save(usuario);
		
		}

		return "redirect:/listarUsuarios";

	}
	
}
package org.oasisDeVida.controller;

import java.util.List;

import javax.validation.Valid;

import org.oasisDeVida.modelo.dao.RolRepository;
import org.oasisDeVida.modelo.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
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
@SessionAttributes("rol")
public class RolController {
	
	@Autowired
	private RolRepository rolRepository;

	@GetMapping("/formularioRol")
	public String registrarRol(Model modelo) {
		modelo.addAttribute("titulo", "Registro de roles");
		modelo.addAttribute("accion", "Registrar");
		Rol rol = new Rol();
		rol.setId(0L);
		rol.setEstado("1");
		modelo.addAttribute("rol", rol);
		return "rol/registrar_rol";

	}

	@PostMapping("/registrarRol")
	public String rolRegistrado(@Valid @ModelAttribute("rol") Rol rol, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {


		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de roles");
			modelo.addAttribute("accion", "Registrar");
			return "rol/registrar_rol";
		}
		Rol rolCodigo = rolRepository.findByCodigo(rol.getCodigo());
		Rol rolNombre = rolRepository.findByNombreRol(rol.getNombreRol());
		if (rolCodigo != null && rolNombre != null) {
			modelo.addAttribute("error", "El código y el nombre del rol ya se encuentra registrado!");
			
		}else {
			
			if(rolNombre != null && rolCodigo == null) {
				modelo.addAttribute("error", "El nombre del rol ya se encuentra registrado!");
				
				
			}else {
				if(rolNombre == null && rolCodigo != null) {
					modelo.addAttribute("error", "El codigo del rol ya se encuentra registrado!");
					
					
				}else {
		rol.setEstado("1");
		modelo.addAttribute("accion", "registrar");
		rolRepository.save(rol);
		
		flash.addFlashAttribute("info", "rol registrado con exito");
		
		status.setComplete();
		return "redirect:/listarRoles";
				}
				modelo.addAttribute("accion", "registrar");
				return "rol/registrar_rol";
				
			}
			modelo.addAttribute("accion", "registrar");
			return "rol/registrar_rol";
	}
		modelo.addAttribute("accion", "registrar");
				return "rol/registrar_rol";
		
		
	}

	@GetMapping("/listarRoles")
	public String listarRoles(Model modelo) {
		modelo.addAttribute("accion", "filtrar");
		modelo.addAttribute("titulo", "Listado de roles");
		modelo.addAttribute("rol", new Rol());
		return "rol/listar_roles";

	}
	
	@PostMapping("/filtrarRol")
	public String filtrar(@Valid @ModelAttribute("rol") Rol rol, BindingResult errores, Model modelo,
		SessionStatus status) {
		List<Rol> roles = null;
		
		 roles = rolRepository.findByCodigoContainingAndNombreRolContainingAndEstado(rol.getCodigo(), rol.getNombreRol(), rol.getEstado());
		
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("rol", rol);
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("titulo", "Listado de roles");

		return "rol/listar_roles";

	}
	
	
	@PostMapping("/consultarRol")
	public String consultarRol(@Valid @ModelAttribute("rol") Rol rol, BindingResult errores,
			Model modelo, SessionStatus status) {
		
		if (rol.getId() >0) {
			rol = rolRepository.findById(rol.getId()).orElse(null);
			if (rol == null) {
				return "redirect:/listar_roles";
			}
		}else {
			return "redirect:/listar_roles";
			
		}
		
		modelo.addAttribute("titulo", "Consulta del rol # " + rol.getId());
		modelo.addAttribute("rol", rol);
		return "rol/consultar_rol";
		
		
	}

	@PostMapping("/modificarRol")
	public String modificarUsuario(@Valid @ModelAttribute("rol") Rol rol, BindingResult errores,
			Model modelo, SessionStatus status) {
		
		if (rol.getId() >0) {
		rol = rolRepository.findById(rol.getId()).orElse(rol);
			if (rol == null) {
				return "redirect:/listar_roles";
			}
		}else {
			return "redirect:/listar_roles";
			
		}
		modelo.addAttribute("rol", rol);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar rol # " + rol.getId());
		
		return "rol/registrar_rol";		
		
	}
	
	@PostMapping("/inactivarRol")
	public String inactivarRol(@ModelAttribute("rol") Rol rol, Model modelo, RedirectAttributes flash ) {
	
		if (rol.getId() >0) {
			rol = rolRepository.findById(rol.getId()).orElse(rol);
			rol.setEstado("0");
			rolRepository.save(rol);
			flash.addFlashAttribute("info", "Rol inactivado con exito");

			}

		return "redirect:/listarRoles";
		
	}
	
	@PostMapping("/activarRol")
	public String activarRol(@ModelAttribute("rol") Rol rol, Model modelo, RedirectAttributes flash ) {
	
		if (rol.getId() >0) {
			rol = rolRepository.findById(rol.getId()).orElse(rol);
			rol.setEstado("1");
			rolRepository.save(rol);
			flash.addFlashAttribute("info", "Rol activado con exito");

			}

		return "redirect:/listarRoles";
		
	}
}
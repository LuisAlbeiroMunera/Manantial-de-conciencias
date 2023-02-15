package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.ICiudadDao;
import org.avismart.modelo.dao.IDepartamentoDao;
import org.avismart.modelo.entity.Ciudad;
import org.avismart.modelo.entity.Departamento;
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
@SessionAttributes("ciudad")
public class CiudadController {
	

	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Autowired
	private ICiudadDao ciudadDao;
	
	@GetMapping("/formularioCiudad")
	public String registrarCiudad(Model modelo) {
		modelo.addAttribute("titulo", "Registro de ciudades");
		modelo.addAttribute("accion", "Registrar");
		Ciudad ciudad = new Ciudad();
		ciudad.setId(0L);
		ciudad.setEstado("1");
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		modelo.addAttribute("ciudad", ciudad);
		return "ciudad/registrar_ciudad";

	}

	@PostMapping("/registrarCiudad")
	public String ciudadRegistrada(@Valid @ModelAttribute("ciudad") Ciudad ciudad, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {



		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de ciudades");
			modelo.addAttribute("accion", "Registrar");
			List<Departamento> departamentos = departamentoDao.findByEstado("1");
			modelo.addAttribute("departamentos", departamentos);
			return "ciudad/registrar_ciudad";
		}
		Ciudad ciudadCodigo = ciudadDao.findByCodigo(ciudad.getCodigo());
		if (ciudadCodigo!= null) {
			modelo.addAttribute("error", "El código de la ciudad ya se encuentra registrada!");
			modelo.addAttribute("titulo", "Registro de ciudades");
			modelo.addAttribute("accion", "Registrar");
			List<Departamento> departamentos = departamentoDao.findByEstado("1");
			modelo.addAttribute("departamentos", departamentos);
		}else {
		ciudad.setEstado("1");
		ciudadDao.save(ciudad);
		flash.addFlashAttribute("info", "Ciudad registrada con exito");
		modelo.addAttribute("titulo", "Registro de ciudades");
		modelo.addAttribute("accion", "Registrar");
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		status.setComplete();
		return "redirect:/listarCiudades";
	
		}
		modelo.addAttribute("titulo", "Registro de ciudades");
		modelo.addAttribute("accion", "Registrar");
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		flash.addFlashAttribute("error", "La ciudad ya se encuentra registrada");
		return "ciudad/registrar_ciudad";
	}
	@GetMapping("/listarCiudades")
	public String listarCiudades(Model modelo) {
		Ciudad ciudad = new Ciudad();
		modelo.addAttribute("accion", "Filtrar");
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		modelo.addAttribute("titulo", "Listado de ciudades");
		modelo.addAttribute("ciudad", ciudad);
		return "ciudad/listar_ciudades";

	}
	
	@PostMapping("/consultarCiudad")
	public String consultarCiudad(@Valid @ModelAttribute("ciudad") Ciudad ciudad, BindingResult errores,
			Model modelo, SessionStatus status) {
		
		if (ciudad.getId() >0) {
			ciudad = ciudadDao.findById(ciudad.getId()).orElse(ciudad);
			if (ciudad == null) {
				List<Departamento> departamentos = departamentoDao.findByEstado("1");
				modelo.addAttribute("departamentos", departamentos);
				return "redirect:/listar_ciudades";
			}
		}else {
			return "redirect:/listar_ciudades";

		}
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del usuario # " + ciudad.getId());
		modelo.addAttribute("ciudad", ciudad);
		return "ciudad/consultar_ciudad";
		
		
	}
	@PostMapping("/filtrarCiudad")
	public String filtrar(@Valid @ModelAttribute("ciudad") Ciudad ciudad, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		List<Ciudad> filtro = null;
		if (ciudad.getDepartamento().getId()== null) {
			filtro= ciudadDao.findByCodigoContainingAndNombreContainingAndEstado(ciudad.getCodigo(), ciudad.getNombre(),ciudad.getEstado());
			modelo.addAttribute("accion", "Filtrar");
		}else {
		filtro= ciudadDao.findByCodigoContainingAndNombreContainingAndEstadoAndDepartamento(ciudad.getCodigo(), ciudad.getNombre(),ciudad.getEstado(), ciudad.getDepartamento());
		}		
		modelo.addAttribute("departamentos", departamentoDao.findAll());
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("ciudad", ciudad);
		modelo.addAttribute("titulo", "Listado de ciudades");
		modelo.addAttribute("ciudades", filtro);
		return "ciudad/listar_ciudades";

	}
	@PostMapping("/modificarCiudad")
	public String modificarUsuario(@Valid @ModelAttribute("ciudad") Ciudad ciudad, BindingResult errores,
			Model modelo, SessionStatus status) {
		List<Departamento> departamentos = departamentoDao.findByEstado("1");
		modelo.addAttribute("departamentos", departamentos);
		
		if (ciudad.getId() >0) {
			ciudad = ciudadDao.findById(ciudad.getId()).orElse(ciudad);
			
			if (ciudad == null) {
				return "redirect:/listar_ciudades";
			}
		}else {
			return "redirect:/listar_ciudades";
			
		}

		modelo.addAttribute("departamentos", departamentos);
		modelo.addAttribute("ciudad", ciudad);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar ciudad # " + ciudad.getId());
		
		return "ciudad/registrar_ciudad";		
		
	}
	
	@PostMapping("/inactivarCiudad")
	public String inactivarCiudad(@ModelAttribute("ciudad") Ciudad ciudad, Model modelo, RedirectAttributes flash ) {
	
		if (ciudad.getId() >0) {
			ciudad = ciudadDao.findById(ciudad.getId()).orElse(ciudad);
			ciudad.setEstado("0");
			ciudadDao.save(ciudad);
			flash.addFlashAttribute("info", "Ciudad inactivada con exito");
			
			}

		return "redirect:/listarCiudades";
		
	}
	
	@PostMapping("/activarCiudad")
	public String activarCiudad(@ModelAttribute("ciudad") Ciudad ciudad, Model modelo, RedirectAttributes flash ) {
	
		if (ciudad.getId() >0) {
			ciudad = ciudadDao.findById(ciudad.getId()).orElse(ciudad);
			ciudad.setEstado("1");
			ciudadDao.save(ciudad);
			flash.addFlashAttribute("info", "Ciudad activada con exito");
			
			}

		return "redirect:/listarCiudades";
		
	}
}
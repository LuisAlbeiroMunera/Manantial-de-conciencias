package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.ICiudadDao;
import org.avismart.modelo.dao.IGranjaDao;
import org.avismart.modelo.entity.Ciudad;
import org.avismart.modelo.entity.Granja;
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
@SessionAttributes("granja")
public class GranjaController {

	@Autowired
	private IGranjaDao granjaService;

	@Autowired
	private ICiudadDao ciudadDao;
	
	@GetMapping("/formularioGranja")
	public String registrarGranja(Model modelo) {
		modelo.addAttribute("titulo", "Registro de granjas");
		modelo.addAttribute("accion", "Registrar");
		Granja granja = new Granja();
		granja.setId(0L);
		granja.setEstado("1");
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		modelo.addAttribute("granja", granja);
		return "granja/registrar_granja";

	}

	@PostMapping("/registrarGranja")
	public String granjaRegistrada(@Valid @ModelAttribute("granja") Granja granja, BindingResult errores,			
			Model modelo, SessionStatus status,RedirectAttributes flash) {
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		if (errores.hasErrors()) {
	
			modelo.addAttribute("ciudades", ciudades);
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de granjas");
			modelo.addAttribute("accion", "Registrar");
			return "granja/registrar_granja";
		}
		Granja granjaCodigo = granjaService.findByCodigo(granja.getCodigo());
		if (granjaCodigo != null) {
			modelo.addAttribute("error", "El código de la granja ya se encuentra registrada!");
			
		}else {
				
			modelo.addAttribute("ciudades", ciudades);
			granja.setEstado("1");
			granjaService.save(granja);
			flash.addFlashAttribute("info", "granja registrada con exito");
			status.setComplete();
			
			return "redirect:/listarGranjas";
	}
		flash.addFlashAttribute("error", "La granja ya se encuentra registrada");
		return "granja/registrar_granja";

	}

	@GetMapping("/listarGranjas")
	public String listarGranjas(Model modelo) {
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		Granja granja = new Granja();

		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("ciudades", ciudades);
		modelo.addAttribute("titulo", "Listado de granjas");
		modelo.addAttribute("granja", granja);
		return "granja/listar_granjas";

	}
	
	@PostMapping("/filtrarGranja")
	public String filtrar(@Valid @ModelAttribute("granja") Granja granja, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Ciudad> ciudades = ciudadDao.findAll();
		modelo.addAttribute("ciudades",ciudades);
		List<Granja> filtro = null;
		if (granja.getCiudad()==null) {
			filtro = granjaService.findByCodigoContainingAndDescripcionContainingAndEstado(granja.getCodigo(), granja.getDescripcion(),granja.getEstado());
			modelo.addAttribute("accion", "Filtrar");
//			filtro= granjaService.findByCodigoContainingAndDescripcionContainingAndEstado(granja.getCodigo(), granja.getDescripcion(),"1");
		}else {
			modelo.addAttribute("accion", "Filtrar");
			filtro= granjaService.findByCodigoContainingAndDescripcionContainingAndEstadoAndCiudad(granja.getCodigo(), granja.getDescripcion(),granja.getEstado(), granja.getCiudad());
		}
		modelo.addAttribute("ciudades", ciudadDao.findAll());
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("ciudades",ciudades);
		modelo.addAttribute("granja", granja);
		modelo.addAttribute("titulo", "Listado de granjas");
		modelo.addAttribute("granjas", filtro);
		return "granja/listar_granjas";

	}
	@PostMapping("/consultarGranja")
	public String consultarGranja(@Valid @ModelAttribute("granja") Granja granja, BindingResult errores,			
			Model modelo, SessionStatus status) {
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		if (granja.getId() >0) {
			granja = granjaService.findById(granja.getId()).orElse(granja);
			if (granja == null) {
				return "redirect:/listar_granjas";
			}
		}else {
			return "redirect:/listar_granjas";
			
		}

		modelo.addAttribute("ciudades",ciudades);
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta de la granja # " + granja.getId());
		modelo.addAttribute("granja", granja);
		return "granja/consultar_granja";
		
		
	}
	@PostMapping("/modificandoGranja")
	public String modificandoGranja(@ModelAttribute("granja") Granja granja, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		modelo.addAttribute("titulo", "Modificando granja");
		modelo.addAttribute("accion", "modificar");
		Granja nuevaGranja = granjaService.findById(granja.getId()).orElse(granja);
		List<Granja> granjas = granjaService.findAll();

		modelo.addAttribute("granjas", granjas);
		modelo.addAttribute("granja", nuevaGranja);
		return "granja/modificar_granja";

	}


	@PostMapping("/modificarGranja")
	public String modificarGranja(@Valid @ModelAttribute("granja") Granja granja, BindingResult errores,			
			SessionStatus status,	Model modelo, RedirectAttributes flash) {
		modelo.addAttribute("titulo", "Modificando granja");
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		modelo.addAttribute("accion", "modificar");
		Granja nuevaGranja = granjaService.findById(granja.getId()).orElse(granja);
		modelo.addAttribute("granja", nuevaGranja);
		if (errores.hasErrors()) {
				
			modelo.addAttribute("ciudades", ciudades);
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "modoficar granja");
			modelo.addAttribute("accion", "modificar");
			
			return "granja/modificar_granja";
		}

		granjaService.save(granja);		
		modelo.addAttribute("ciudades", ciudades);
		flash.addFlashAttribute("info", "granja modificada con exito");
		status.setComplete();
		return "redirect:/listarGranjas";			
		
	}
	
	@PostMapping("/inactivarGranja")
	public String inactivarGranja(@ModelAttribute("granja") Granja granja, Model modelo, RedirectAttributes flash) {
	
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		if (granja.getId() > 0) {
			granja = granjaService.findById(granja.getId()).orElse(granja);
			granja.setEstado("0");
			flash.addFlashAttribute("info", "granja inactivada con exito");

			granjaService.save(granja);
			
		
		}

		return "redirect:/listargranjas";
		
	}
	
	@PostMapping("/activarGranja")
	public String activarGranja(@ModelAttribute("granja") Granja granja, Model modelo, RedirectAttributes flash) {
	
		List<Ciudad> ciudades = ciudadDao.findByEstado("1");
		modelo.addAttribute("ciudades",ciudades);
		if (granja.getId() > 0) {
			granja = granjaService.findById(granja.getId()).orElse(granja);
			granja.setEstado("1");
			flash.addFlashAttribute("info", "granja activada con exito");

			granjaService.save(granja);
		
		
		}

		return "redirect:/listargranjas";
		
	}
}
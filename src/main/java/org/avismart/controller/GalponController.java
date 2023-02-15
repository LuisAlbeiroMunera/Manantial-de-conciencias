package org.avismart.controller;


import java.util.List;
import javax.validation.Valid;
import org.avismart.modelo.dao.IGalponDao;
import org.avismart.modelo.dao.IGranjaDao;
import org.avismart.modelo.entity.Galpon;
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
@SessionAttributes("galpon")
public class GalponController {
	
	@Autowired
	private IGalponDao galponService;
	@Autowired
	private IGranjaDao granjaservice;

	@GetMapping("/formularioGalpon")
	public String registrarGalpon(Model modelo) {
		modelo.addAttribute("titulo", "Registro de galpones");
		modelo.addAttribute("accion", "Registrar");
		Galpon galpon = new Galpon();
		galpon.setId(0L);
		galpon.setEstado("1");
		List<Granja> granjas = granjaservice.findByEstado("1");
		modelo.addAttribute("granjas", granjas);
		modelo.addAttribute("galpon", galpon);
		return "galpon/registrar_galpon";

	}

	@PostMapping("/registrarGalpon")
	public String productoRegistrado(@Valid @ModelAttribute("galpon") Galpon galpon, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
		System.out.println("Paso por /registrarGalpon :::::::");

		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de galpones");
			modelo.addAttribute("accion", "Registrar");
			List<Granja> granjas = granjaservice.findByEstado("1");
			modelo.addAttribute("granjas", granjas);
			return "galpon/registrar_galpon";
		}
		Galpon galponCodigo = galponService.findByCodigo(galpon.getCodigo());
		if (galponCodigo != null) {
			modelo.addAttribute("error", "El código del galpon ya se encuentra registrado!");
			List<Granja> granjas = granjaservice.findByEstado("1");
			modelo.addAttribute("granjas", granjas);
		}else {
			galpon.setEstado("1");
			galponService.save(galpon);
			flash.addFlashAttribute("info", "Galpon registrado con exito");
			
			status.setComplete();
			List<Granja> granjas = granjaservice.findByEstado("1");
			modelo.addAttribute("granjas", granjas);
			return "redirect:/listarGalpones";
	}
		flash.addFlashAttribute("error", "El galpon ya se encuentra registrado");
		return "galpon/registrar_galpon";
	
	}

	@GetMapping("/listarGalpones")
	public String listarGalpon(Model modelo) {
		Galpon galpon = new Galpon();
		
		modelo.addAttribute("titulo", "Listado de galpones");
		List<Granja> granjas = granjaservice.findByEstado("1");
		modelo.addAttribute("accion","filtrar");
		modelo.addAttribute("granjas", granjas);
		modelo.addAttribute("galpon",galpon);
		return "galpon/listar_galpones";

	}
	@PostMapping("/consultarGalpon")
	public String consultarGalpon(@Valid @ModelAttribute("galpon") Galpon galpon, BindingResult errores,
			Model modelo, SessionStatus status) {
	
		if (galpon.getId() >0) {
			galpon = galponService.findById(galpon.getId()).orElse(galpon);
			if (galpon == null) {
				List<Granja> granjas = granjaservice.findByEstado("1");
				modelo.addAttribute("granjas", granjas);
				modelo.addAttribute("accion","filtrar");
				return "redirect:/listar_galpones";
			}
		}else {
			modelo.addAttribute("accion","filtrar");
			return "redirect:/listar_galpones";
			
		}
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del galpon # " + galpon.getId());
		modelo.addAttribute("galpon", galpon);
		List<Granja> granjas = granjaservice.findByEstado("1");
		modelo.addAttribute("granjas", granjas);
		return "galpon/consultar_galpon";
		
		
	}

	@PostMapping("/filtrarGalpon")
	public String filtrar(@ModelAttribute("galpon") Galpon galpon, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Granja> granjas = granjaservice.findByEstado("1");
		modelo.addAttribute("granjas", granjas);
		List<Galpon> filtro = null;
		modelo.addAttribute("accion","filtrar");
		if (galpon.getGranja().getId()==null) {
			filtro= galponService.findByCodigoContainingAndDescripcionContainingAndEstado(galpon.getCodigo(), galpon.getDescripcion(),"1");
			modelo.addAttribute("accion","filtrar");
		}else {
			modelo.addAttribute("accion","filtrar");
			filtro= galponService.findByCodigoContainingAndDescripcionContainingAndEstadoAndGranjaContaining(galpon.getCodigo(), galpon.getDescripcion(),"1", galpon.getGranja());
	}		
	
		modelo.addAttribute("granjas", granjas);
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("galpon", galpon);
		modelo.addAttribute("titulo", "Listado de galpones");
		modelo.addAttribute("galpones", filtro);
		return "galpon/listar_galpones";

	}
	

	@PostMapping("/modificarGalpon")
	public String modificargalpon(@Valid @ModelAttribute("galpon") Galpon galpon, BindingResult errores,
			Model modelo, SessionStatus status) {
		List<Granja> granjas = granjaservice.findByEstado("1");
		modelo.addAttribute("granjas", granjas);
		if (galpon.getId() >0) {
			galpon = galponService.findById(galpon.getId()).orElse(galpon);
			if (galpon == null) {
				return "redirect:/listar_galpones";
			}
		}else {
			return "redirect:/listar_galpones";
			
		}

		modelo.addAttribute("granjas", granjas);
		modelo.addAttribute("galpon", galpon);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar galpon # " + galpon.getId());
		
		return "galpon/registrar_galpon";		
		
	}
	
	@PostMapping("/inactivarGalpon")
	public String inactivarGalpon(@ModelAttribute("galpon") Galpon galpon, Model modelo, RedirectAttributes flash ) {
		if (galpon.getId() >0) {
			galpon.setEstado("0");
			flash.addFlashAttribute("info", "galpon inactivado con exito");

			galponService.save(galpon);
			}

		return "redirect:/listarGalpones";
		
	}


}
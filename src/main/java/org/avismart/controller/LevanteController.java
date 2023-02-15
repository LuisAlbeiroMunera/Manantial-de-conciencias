package org.avismart.controller;

import java.util.List;
import javax.validation.Valid;

import org.avismart.modelo.dao.IGalponDao;
import org.avismart.modelo.dao.LevanteDAO;
import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.entity.Galpon;
import org.avismart.modelo.entity.Levante;
import org.avismart.modelo.entity.Lote;
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
@SessionAttributes("levante")
public class LevanteController {

	@Autowired
	private LevanteDAO levanteDAO;
	
	@Autowired
	private IGalponDao galponDAO;
	
	@Autowired
	private LoteDAO loteDAO;

	@GetMapping("/formularioLevante")
	public String registrarLevante(Model modelo) {
		List<Galpon> galpones = galponDAO.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		List<Lote> lotes = loteDAO.findByEstado("1");
		modelo.addAttribute("lotes", lotes);
	
		modelo.addAttribute("titulo", "Registro de levantes");
		modelo.addAttribute("accion", "Registrar");
		Levante levante = new Levante();
		levante.setId(0L);
		levante.setEstado("1");
		modelo.addAttribute("levante", levante);
		return "levante/registrar_levante";

	}

	@PostMapping("/registrarLevante")
	public String levanteRegistrado(@Valid @ModelAttribute("levante") Levante levante, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {

		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de levantes");
			modelo.addAttribute("accion", "Registrar");
			List<Galpon> galpones = galponDAO.findByEstado("1");
			modelo.addAttribute("galpones", galpones);
			List<Lote> lotes = loteDAO.findByEstado("1");
			modelo.addAttribute("lotes", lotes);
			return "levante/registrar_levante";
		}
		Levante codigoLevante = levanteDAO.findByCodigo(levante.getCodigo());
		if (codigoLevante != null) {
			modelo.addAttribute("titulo", "Registro de levantes");
			modelo.addAttribute("accion", "Registrar");
			List<Galpon> galpones = galponDAO.findByEstado("1");
			modelo.addAttribute("galpones", galpones);
			List<Lote> lotes = loteDAO.findByEstado("1");
			modelo.addAttribute("lotes", lotes);
			modelo.addAttribute("error", "El código del usuario ya se encuentran registrado!");
			
		}else {
		levanteDAO.save(levante);
		status.setComplete();
		flash.addFlashAttribute("info", "Usuario registrado con exito");
		
		return "redirect:/listarLevantes";
	}
		return "levante/registrar_levante";
	}

	@GetMapping("/listarLevantes")
	public String listarLevantes(Model modelo) {
		modelo.addAttribute("accion", "Filtrar");
		List<Galpon> galpones = galponDAO.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		List<Lote> lotes = loteDAO.findByEstado("1");
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("titulo", "Listado de levantes");
		modelo.addAttribute("levante", new Levante());
		return "levante/listar_levantes";

	}
	
	@PostMapping("/filtrarLevante")
	public String filtrarLevante(@Valid @ModelAttribute("levante") Levante levante, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Galpon> galpones = galponDAO.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		List<Lote> lotes = loteDAO.findByEstado("1");
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("accion", "Filtrar");
		List<Levante> filtro = null;
		if (levante.getGalpon()== null && levante.getLote()== null) {
			filtro= levanteDAO.findByCodigoContainingAndDescripcionContainingAndEstado(levante.getCodigo(), levante.getDescripcion(),levante.getEstado());
			modelo.addAttribute("accion", "Filtrar");
		}else {
	
			if (levante.getGalpon()!= null && levante.getLote()== null) {
				filtro= levanteDAO.findByCodigoContainingAndDescripcionContainingAndEstadoAndGalpon(levante.getCodigo(), levante.getDescripcion(),levante.getEstado(), levante.getGalpon());
				modelo.addAttribute("accion", "Filtrar");
			}else			
				if (levante.getGalpon()== null && levante.getLote()!= null) {
				filtro= levanteDAO.findByCodigoContainingAndDescripcionContainingAndEstadoAndLote(levante.getCodigo(), levante.getDescripcion(),levante.getEstado(), levante.getLote());
				modelo.addAttribute("accion", "Filtrar");
			}else			
				if (levante.getGalpon()!= null && levante.getLote()!= null) {
				filtro= levanteDAO.findByCodigoContainingAndDescripcionContainingAndEstadoAndLoteAndGalpon(levante.getCodigo(), levante.getDescripcion(),levante.getEstado(), levante.getLote(), levante.getGalpon());
				modelo.addAttribute("accion", "Filtrar");
			}}		

		modelo.addAttribute("accion", "Filtrar");
	
		modelo.addAttribute("titulo", "Listado de levantes");
		modelo.addAttribute("levantes", filtro);
		return "levante/listar_levantes";

	}
	@PostMapping("/consultarLevante")
	public String consultarLevante(@Valid @ModelAttribute("levante") Levante levante, BindingResult errores,
			Model modelo, SessionStatus status) {
		List<Galpon> galpones = galponDAO.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		List<Lote> lotes = loteDAO.findByEstado("1");
		modelo.addAttribute("lotes", lotes);
		if (levante.getId() >0) {
			levante = levanteDAO.findById(levante.getId()).orElse(levante);
			if (levante == null) {
				return "redirect:/listar_levantes";
			}
		}else {
			return "redirect:/listar_levantes";
			
		}
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del levante # " + levante.getId());
		modelo.addAttribute("levante", levante);
		return "levante/consultar_levante";
		
		
	}


	@PostMapping("/modificandoLevante")
	public String modificandoLevante(@Valid @ModelAttribute("levante") Levante levante, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
		List<Galpon> galpones = galponDAO.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		List<Lote> lotes = loteDAO.findByEstado("1");
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("titulo", "Modificando levante");
		modelo.addAttribute("accion", "modificar");
		Levante nuevoLevante = levanteDAO.findById(levante.getId()).orElse(levante);
		modelo.addAttribute("levante", nuevoLevante);

		return "levante/modificar_levante";		
		
	}
	@PostMapping("/modificarLevante")
	public String modificarLevante(@Valid @ModelAttribute("levante") Levante levante, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
	
		modelo.addAttribute("titulo", "Modificando levante");
		modelo.addAttribute("accion", "modificar");
		Levante nuevoLevante = levanteDAO.findById(levante.getId()).orElse(levante);
		modelo.addAttribute("levante", nuevoLevante);
		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "modoficar levante");
			modelo.addAttribute("accion", "modificar");
			return "levante/modificar_levante";
		}

		levanteDAO.save(levante);
		flash.addFlashAttribute("info", "levante modificado con exito");
		status.setComplete();
		return "redirect:/listarLevantes";		
		
	}
	
	@PostMapping("/inactivarLevante")
	public String inactivarLevante(@ModelAttribute("levante") Levante levante, Model modelo, RedirectAttributes flash) {

		if (levante.getId() >0) {
			levante = levanteDAO.findById(levante.getId()).orElse(levante);
			levante.setEstado("0");
			flash.addFlashAttribute("info", "levante inactivado con exito");
			levanteDAO.save(levante);
			}

		return "redirect:/listarLevantes";
		
	}
	
	@PostMapping("/activarLevante")
	public String activarLevante(@ModelAttribute("levante") Levante levante, Model modelo, RedirectAttributes flash) {

		if (levante.getId() >0) {
			levante = levanteDAO.findById(levante.getId()).orElse(levante);
			
			levante.setEstado("1");
			flash.addFlashAttribute("info", "Levante activado con exito");
			levanteDAO.save(levante);
			}

		return "redirect:/listarLevantes";
		
	}
}
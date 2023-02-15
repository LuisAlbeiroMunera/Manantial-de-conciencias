package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.Rol;
import org.avismart.modelo.entity.Usuario;
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
@SessionAttributes("lote")
public class LoteController {

	@Autowired
	private LoteDAO loteDAO;

	@GetMapping("/formularioLote")
	public String registrarLote(Model modelo) {
		modelo.addAttribute("titulo", "Registro de lotes");
		modelo.addAttribute("accion", "Registrar");
		Lote lote = new Lote();
		lote.setId(0L);
		lote.setEstado("1");
		modelo.addAttribute("lote", lote);
		return "lote/registrar_lote";

	}

	@PostMapping("/registrarLote")
	public String loteRegistrado(@Valid @ModelAttribute("lote") Lote lote, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {


		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de lotes");
			modelo.addAttribute("accion", "Registrar");
			return "lote/registrar_lote";
		}
		Lote loteCodigo = loteDAO.findByCodigo(lote.getCodigo());
			if (loteCodigo != null) {
			modelo.addAttribute("error", "El código del lote ya se encuentra registrado!");
			
		}else {
			
		lote.setEstado("1");
		modelo.addAttribute("accion", "registrar");
		loteDAO.save(lote);
		
		flash.addFlashAttribute("info", "lote registrado con exito");
		
		status.setComplete();
		return "redirect:/listarLotes";
				}
	
		modelo.addAttribute("accion", "registrar");
				return "lote/registrar_lote";
		
		
	}


	@GetMapping("/listarLotes")
	public String listarLotes(Model modelo) {
		modelo.addAttribute("titulo", "Listado de lotes");
		modelo.addAttribute("accion", "filtrar");
		modelo.addAttribute("lote", new Lote());
		return "lote/listar_lotes";

	}
	
	@PostMapping("/filtrarLotes")
	public String filtrar(@Valid @ModelAttribute("lote") Lote lote, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Lote> filtro = null;
	
		filtro= loteDAO.findByCodigoContainingAndDescripcionContainingAndEstado(lote.getCodigo(), lote.getDescripcion(),lote.getEstado());

		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("lote", lote);
		modelo.addAttribute("titulo", "Listado de lotes");
		modelo.addAttribute("lotes", filtro);
		return "lote/listar_lotes";

	}

	@PostMapping("/consultarLote")
	public String consultarLote(@Valid @ModelAttribute("lote") Lote lote, BindingResult errores,
			Model modelo, SessionStatus status) {
	
		if (lote.getId() >0) {
			lote = loteDAO.findById(lote.getId()).orElse(null);
			if (lote == null) {
				return "redirect:/listar_lotes";
			}
		}else {
			return "redirect:/listar_lotes";
			
		}
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del lote # " + lote.getId());
		modelo.addAttribute("lote", lote);
		return "lote/consultar_lote";
		
		
	}

	@PostMapping("/modificandoLote")
	public String modificandoLote(@ModelAttribute("lote") Lote lote, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {

		modelo.addAttribute("titulo", "Modificando lote");
		modelo.addAttribute("accion", "modificar");
		Lote nuevoLote = loteDAO.findById(lote.getId()).orElse(lote);
		List<Lote> lotes = loteDAO.findAll();
		modelo.addAttribute("lotes", lotes);
		modelo.addAttribute("lote", nuevoLote);
		return "lote/modificar_lote";

	}
	
	@PostMapping("/modificarLote")
	public String modificarLote(@Valid @ModelAttribute("lote") Lote lote, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
	
		modelo.addAttribute("titulo", "Modificando lote");
		modelo.addAttribute("accion", "modificar");
		Lote nuevoLote = loteDAO.findById(lote.getId()).orElse(lote);
		modelo.addAttribute("lote", nuevoLote);
		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "modoficar lote");
			modelo.addAttribute("accion", "modificar");
			return "lote/modificar_lote";
		}

		loteDAO.save(lote);
		flash.addFlashAttribute("info", "lote modificado con exito");
		status.setComplete();
		return "redirect:/listarLotes";		
		
	}
	
	@PostMapping("/inactivarLote")
	public String inactivarLote(@ModelAttribute("lote") Lote lote, Model modelo, RedirectAttributes flash) {
	
		if (lote.getId() >0) {
			lote = loteDAO.findById(lote.getId()).orElse(lote);
			lote.setEstado("0");
			flash.addFlashAttribute("info", "lote inactivado con exito");
			loteDAO.save(lote);
			}

		return "redirect:/listarLotes";
		
	}
	
	@PostMapping("/activarLote")
	public String activarLote(@ModelAttribute("lote") Lote lote, Model modelo, RedirectAttributes flash) {
	
		if (lote.getId() >0) {
			lote = loteDAO.findById(lote.getId()).orElse(lote);
			lote.setEstado("1");
			flash.addFlashAttribute("info", "lote activado con exito");
			loteDAO.save(lote);
			}

		return "redirect:/listarLotes";
		
	}
}
package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.dao.RegistroArduinoDAO;
import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.RegistroArduino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("registroArduino")
public class RegistroArduinoController {

	@Autowired
	private RegistroArduinoDAO registroArduinoDAO;

	@GetMapping("/formularioRegistroArduino")
	public String registrarRegistroArduino(Model modelo) {
		modelo.addAttribute("titulo", "Registro de Arduino");
		modelo.addAttribute("accion", "Registrar");
		RegistroArduino registroArduino = new RegistroArduino();
		registroArduino.setId(0L);
		modelo.addAttribute("registroArduino", registroArduino);
		return "registroArduino/registrarArduino";

	}

	@PostMapping("/registrarArduino")
	public String registroArduinoRegistrado(@Valid @ModelAttribute("registroArduino") RegistroArduino registroArduino, 
			BindingResult errores, Model modelo, SessionStatus status) {


		if (errores.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de Arduino");
			modelo.addAttribute("accion", "Registrar");
			return "registroArduino/registros_arduino";
		}
		registroArduinoDAO.save(registroArduino);
		status.setComplete();
		return "redirect:/listarRegistrosArduino";
	}

	@GetMapping("/listarRegistrosArduino")
	public String listarRegistrosArduino(Model modelo) {
		List<RegistroArduino> registros_arduino = registroArduinoDAO.findAll();
		modelo.addAttribute("titulo", "Registros de arduino");
		modelo.addAttribute("registros_arduino", registros_arduino);
		modelo.addAttribute("registroArduino", new RegistroArduino());
		return "registroArduino/listar_registros_Arduino";

	}
	@PostMapping("/consultarRetrosArduino")
	public String consultarRestrosArduino(@Valid @ModelAttribute("registrosArduino") RegistroArduino registroArduino, BindingResult errores,
			Model modelo, SessionStatus status) {
	
		if (registroArduino.getId() >0) {
			registroArduino = registroArduinoDAO.findById(registroArduino.getId()).orElse(registroArduino);
			if (registroArduino == null) {
				return "redirect:/listar_registros_Arduino";
			}
		}else {
			return "redirect:/listar_registros_Arduino";
			
		}
		
		modelo.addAttribute("titulo", "Consulta del registro # " + registroArduino.getId());
		modelo.addAttribute("registroArduino", registroArduino);
		return "registrosArduino/listar_registros_Arduino";
				
	}

/* *********SE comenta mientras se define si se modifican los registros del arduino

	@PostMapping("/modificarLote")
	public String modificarLote(@Valid @ModelAttribute("lote") Lote lote, BindingResult errores,
			Model modelo, SessionStatus status) {
	
		if (lote.getId() >0) {
		lote = loteDAO.findById(lote.getId()).orElse(lote);
			if (lote == null) {
				return "redirect:/listar_lotes";
			}
		}else {
			return "redirect:/listar_lotes";
			
		}
		modelo.addAttribute("lote", lote);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar lote # " + lote.getId());
		
		return "lote/registrar_lote";		
		
	}
	
	@PostMapping("/inactivarLote")
	public String inactivarRol(@ModelAttribute("lote") Lote lote, Model modelo ) {
	
		if (lote.getId() >0) {
			
			loteDAO.deleteById(lote.getId());
			}

		return "redirect:/listarLotes";
		
	}*/
}
	
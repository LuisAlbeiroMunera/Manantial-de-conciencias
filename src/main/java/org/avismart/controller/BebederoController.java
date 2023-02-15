package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;

import org.avismart.modelo.dao.IBebederoDAO;
import org.avismart.modelo.dao.ICiudadDao;
import org.avismart.modelo.dao.IDepartamentoDao;
import org.avismart.modelo.dao.IGalponDao;
import org.avismart.modelo.entity.Bebedero;
import org.avismart.modelo.entity.Ciudad;
import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Galpon;
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
@SessionAttributes("bebedero")
public class BebederoController {
	

	@Autowired
	private IBebederoDAO bebederoDao;
	
	@Autowired
	private IGalponDao galponDao;
	
	@GetMapping("/formularioBebedero")
	public String registrarBebedero(Model modelo) {
		modelo.addAttribute("titulo", "Registro de bebederos");
		modelo.addAttribute("accion", "Registrar");
		Bebedero bebedero = new Bebedero();
		bebedero.setId(0L);
		bebedero.setEstado("1");
		List<Galpon> galpones = galponDao.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		modelo.addAttribute("bebedero", bebedero);
		return "bebedero/registrar_bebedero";

	}

	@PostMapping("/registrarBebedero")
	public String bebederoRegistrado(@Valid @ModelAttribute("bebedero") Bebedero bebedero, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {



		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de bebedero");
			modelo.addAttribute("accion", "Registrar");
			List<Galpon> galpones = galponDao.findByEstado("1");
			modelo.addAttribute("galpones", galpones);
			return "bebedero/registrar_bebedero";
		}
		Bebedero bebederoCodigo = bebederoDao.findByCodigo(bebedero.getCodigo());
		if (bebederoCodigo != null) {
			modelo.addAttribute("error", "El código del bebedero ya se encuentra registrado!");
			modelo.addAttribute("titulo", "Registro de bebederos");
			modelo.addAttribute("accion", "Registrar");
			List<Galpon> galpones = galponDao.findByEstado("1");
			modelo.addAttribute("galpones", galpones);
		}else {
		bebedero.setEstado("1");
		bebederoDao.save(bebedero);
		flash.addFlashAttribute("info", "Bebedero registrado con exito");
		modelo.addAttribute("titulo", "Registro de bebedero");
		modelo.addAttribute("accion", "Registrar");
		List<Galpon> galpones = galponDao.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		status.setComplete();
		return "redirect:/listarBebederos";
	
		}
		modelo.addAttribute("error", "El código del bebedero ya se encuentra registrado!");
		modelo.addAttribute("titulo", "Registro de bebederos");
		modelo.addAttribute("accion", "Registrar");
		List<Galpon> galpones = galponDao.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		return "bebedero/registrar_bebedero";
	}
	@GetMapping("/listarBebederos")
	public String listarBebederos(Model modelo) {
		Bebedero bebedero = new Bebedero();
		modelo.addAttribute("accion", "Filtrar");

		modelo.addAttribute("titulo", "Listado de bebederos");
		modelo.addAttribute("bebedero", bebedero);
		return "bebedero/listar_bebederos";

	}
	
	@PostMapping("/consultarBebedero")
	public String consultarCiudad(@Valid @ModelAttribute("bebedero") Bebedero bebedero, BindingResult errores,
			Model modelo, SessionStatus status) {
		
		if (bebedero.getId() >0) {
			bebedero = bebederoDao.findById(bebedero.getId()).orElse(bebedero);
			if (bebedero == null) {
				List<Galpon> galpones = galponDao.findByEstado("1");
				modelo.addAttribute("galpones", galpones);
				return "redirect:/listar_bebederos";
			}
		}else {
			return "redirect:/listar_bebederos";

		}
		List<Galpon> galpones = galponDao.findByEstado("1");
		modelo.addAttribute("galpones", galpones);
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del bebedero # " + bebedero.getId());
		modelo.addAttribute("bebedero", bebedero);
		return "bebedero/consultar_bebedero";
		
		
	}
	@PostMapping("/filtrarBebederos")
	public String filtrar(@Valid @ModelAttribute("bebedero") Bebedero bebedero, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Galpon> galpones = galponDao.findByEstado(bebedero.getEstado());
		modelo.addAttribute("galpones", galpones);
		List<Bebedero> filtro = null;
		filtro= bebederoDao.findByEstado(bebedero.getEstado());
		if (bebedero.getGalpon()== null) {
			filtro= bebederoDao.findByEstado(bebedero.getEstado());
			modelo.addAttribute("accion", "Filtrar");
		}else {
		filtro= bebederoDao.findByEstado("1");
		}		

		modelo.addAttribute("accion", "Filtrar");
		filtro= bebederoDao.findByEstado("1");
		modelo.addAttribute("bebederos", filtro);
		return "bebedero/listar_bebederos";

	}
	@PostMapping("/modificarBebedero")
	public String modificarBebedero(@Valid @ModelAttribute("bebedero") Bebedero bebedero, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {
	
		modelo.addAttribute("titulo", "Modificando bebedero");
		modelo.addAttribute("accion", "modificar");
		Bebedero nuevoBebedero = bebederoDao.findById(bebedero.getId()).orElse(bebedero);
		modelo.addAttribute("bebedero", nuevoBebedero);
		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la informaci&oacute;n del formulario!");
			modelo.addAttribute("titulo", "modoficar bebedero");
			modelo.addAttribute("accion", "modificar");
			return "bebedero/modificar_bebedero";
		}

		bebederoDao.save(bebedero);
		flash.addFlashAttribute("info", "bebedero modificado con exito");
		status.setComplete();
		return "redirect:/listarBebederos";		
		
	}
	@PostMapping("/inactivarBebedero")
	public String inactivarCiudad(@ModelAttribute("bebedero") Bebedero bebedero, Model modelo, RedirectAttributes flash ) {
	
		if (bebedero.getId() >0) {
			bebedero = bebederoDao.findById(bebedero.getId()).orElse(bebedero);
			bebedero.setEstado("0");
			bebederoDao.save(bebedero);
			flash.addFlashAttribute("info", "Bebedero inactivado con exito");
			
			}

		return "redirect:/listarBebederos";
		
	}
	
	@PostMapping("/activarBebedero")
	public String activarBebedero(@ModelAttribute("bebedero") Bebedero bebedero, Model modelo, RedirectAttributes flash ) {
	
		if (bebedero.getId() >0) {
			bebedero = bebederoDao.findById(bebedero.getId()).orElse(bebedero);
			bebedero.setEstado("1");
			bebederoDao.save(bebedero);
			flash.addFlashAttribute("info", "Bebedero activado con exito");
			
			}

		return "redirect:/listarBebederos";
		
	}
}
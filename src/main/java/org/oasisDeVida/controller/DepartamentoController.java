package org.oasisDeVida.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.oasisDeVida.modelo.dao.IDepartamentoDao;
import org.oasisDeVida.modelo.entity.Departamento;
import org.oasisDeVida.modelo.entity.Usuario;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import reportes.UsuarioDataSource;

@Controller
@SessionAttributes("departamento")
public class DepartamentoController {

	@Autowired
	private IDepartamentoDao departamentoService;

	@GetMapping("/formularioDepartamento")
	public String registrarDepartamento(Model modelo) {
		modelo.addAttribute("titulo", "Registro de departamentos");
		modelo.addAttribute("accion", "Registrar");
		Departamento departamento = new Departamento();
		departamento.setId(0L);
		departamento.setEstado("1");
		modelo.addAttribute("departamento", departamento);
		return "departamento/registrar_departamento";

	}

	@PostMapping("/registrarDepartamento")
	public String registrarDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult errores,
			Model modelo, SessionStatus status, RedirectAttributes flash) {

		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de departamentos");
			modelo.addAttribute("accion", "Registrar");
			return "departamento/registrar_departamento";
		}
			Departamento departamentoCod = departamentoService.findByCodigo(departamento.getCodigo());
		if (departamentoCod != null) {
			modelo.addAttribute("error", "El código del departamento ya se encuentra registrado!");
			
		}else {
			departamento.setEstado("1");
		departamentoService.save(departamento);
		flash.addFlashAttribute("info", "Departamento registrado con exito");
		
		status.setComplete();
		return "redirect:/listarDepartamentos";
	}
		flash.addFlashAttribute("error", "El departamento ya se encuentra registrado");
		return "departamento/registrar_departamento";
	
	}

	@GetMapping("/listarDepartamentos")
	public String listarDepartamentos(Model modelo) {
		Departamento departamento = new Departamento();
		modelo.addAttribute("accion", "filtrar");
		modelo.addAttribute("titulo", "Listado de departamentos");
		modelo.addAttribute("departamento", departamento);
		return "departamento/listar_departamentos";
	}
	
	@PostMapping("/consultarDepartamento")
	public String consultarDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult errores,
			Model modelo, SessionStatus status) {

		if (departamento.getId() >0) {
			departamento = departamentoService.findById(departamento.getId()).orElse(departamento);
			if (departamento == null) {
		
				return "redirect:/listar_departamentos";
			}
		}else {
			return "redirect:/listar_departamentos";
			
		}
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del departamento # " + departamento.getId());
		modelo.addAttribute("departamento", departamento);
		return "departamento/consultar_departamento";
		
		
	}
	@PostMapping("/filtrarDepartamento")
	public String filtrar(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Departamento> filtro = null;

		filtro= departamentoService.findByCodigoContainingAndNombreContainingAndEstado(departamento.getCodigo(), departamento.getNombre(),departamento.getEstado());
				
	
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("departamento", departamento);
		modelo.addAttribute("titulo", "Listado de departamentos");
		modelo.addAttribute("departamentos", filtro);
		return "departamento/listar_departamentos";

	}

	@PostMapping("/modificarDepartamento")
	public String modificarDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult errores,
			Model modelo, SessionStatus status) {

		if (departamento.getId() >0) {
			departamento = departamentoService.findById(departamento.getId()).orElse(departamento);
			if (departamento == null) {
				return "redirect:/listar_departamentos";
			}
		}else {
			return "redirect:/listar_departamentos";
			
		}
		modelo.addAttribute("departamento", departamento);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar departamento # " + departamento.getId());
		
		return "departamento/registrar_departamento";		
		
	}
	
	@PostMapping("/inactivarDepartamento")
	public String inactivarDepartamento(@ModelAttribute("departamento") Departamento  departamento, Model modelo, RedirectAttributes flash ) {
	
		if (departamento.getId() >0) {
			
			departamento = departamentoService.findById(departamento.getId()).orElse(departamento);
			departamento.setEstado("0");
			flash.addFlashAttribute("info", "departamento inactivado con exito");
			departamentoService.save(departamento);
		}

		return "redirect:/listarDepartamentos";
		
	}
	@PostMapping("/activarDepartamento")
	public String activarDepartamento(@ModelAttribute("departamento") Departamento  departamento, Model modelo, RedirectAttributes flash ) {
	
		if (departamento.getId() >0) {
			
			departamento = departamentoService.findById(departamento.getId()).orElse(departamento);
			departamento.setEstado("1");
			flash.addFlashAttribute("info", "departamento activado con exito");
			departamentoService.save(departamento);
		}

		return "redirect:/listarDepartamentos";
		
	}
	
}
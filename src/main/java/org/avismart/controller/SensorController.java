package org.avismart.controller;

import java.util.List;

import javax.validation.Valid;


import org.avismart.modelo.dao.IBebederoDAO;

import org.avismart.modelo.dao.RegistroArduinoDAO;
import org.avismart.modelo.dao.SensorDAO;
import org.avismart.modelo.entity.Bebedero;

import org.avismart.modelo.entity.Sensor;
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
@SessionAttributes("sensor")
public class SensorController {

	@Autowired
	private SensorDAO sensorDAO;
	
	@Autowired
	private IBebederoDAO bebederoDAO;
	
	@Autowired
	private RegistroArduinoDAO registroArduinoDAO;
	
	@GetMapping("/formularioSensor")
	public String registrarGranja(Model modelo) {
		modelo.addAttribute("titulo", "Registro de sensores");
		modelo.addAttribute("accion", "Registrar");
		Sensor sensor = new Sensor();
		sensor.setId(0L);
		
		
		sensor.setEstado("1");
		List<Bebedero> bebederos = bebederoDAO.findAll();
		modelo.addAttribute("bebederos",bebederos);
		modelo.addAttribute("sensor", sensor);
		return "sensor/registrar_sensor";

	}

	@PostMapping("/registrarSensor")
	public String granjaRegistrada(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult errores,			
			Model modelo, SessionStatus status,RedirectAttributes flash) {
		List<Bebedero> bebederos = bebederoDAO.findAll();
		modelo.addAttribute("bebederos",bebederos);
		if (errores.hasErrors()) {
			modelo.addAttribute("error", "Revisa la información del formulario!");
			modelo.addAttribute("titulo", "Registro de sensores");
			modelo.addAttribute("accion", "Registrar");
			return "sensor/registrar_sensor";
		}
			sensor.setEstado("1");
			sensorDAO.save(sensor);
			flash.addFlashAttribute("info", "Sensor registrado con exito");
			status.setComplete();
			
			return "redirect:/listarSensores";


	}

	@GetMapping("/listarSensores")
	public String listarGranjas(Model modelo) {
		Sensor sensor = new Sensor();
		modelo.addAttribute("accion", "Filtrar");
//		List<Sensor> sensores = sensorDAO.findAll();
//		modelo.addAttribute("sensores", sensores);
		modelo.addAttribute("titulo", "Listado de sensores");
		modelo.addAttribute("sensor", sensor);
		return "sensor/listar_sensores";

	}
	
	@PostMapping("/filtrarSensores")
	public String filtrar(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult errores, Model modelo,
			SessionStatus status) {
		List<Sensor> filtro = null;
		
		if (sensor.getDescripcion().isEmpty() && sensor .getCodigo().isEmpty()) {
			filtro = sensorDAO.findByEstado(sensor.getEstado());
//			filtro= granjaService.findByCodigoContainingAndDescripcionContainingAndEstado(granja.getCodigo(), granja.getDescripcion(),"1");
		}else {
			filtro= sensorDAO.findByCodigoContainingAndDescripcionContainingAndEstado(sensor.getCodigo(), sensor.getDescripcion(),sensor.getEstado());
		}
		modelo.addAttribute("sensores", sensorDAO.findAll());
		modelo.addAttribute("accion", "Filtrar");
		modelo.addAttribute("sensor", sensor);
		modelo.addAttribute("titulo", "Listado de sensores");
		modelo.addAttribute("sensores", filtro);
		return "sensor/listar_sensores";

	}
	@PostMapping("/consultarSensor")
	public String consultarSensor(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult errores,			
			Model modelo, SessionStatus status) {
		if (sensor.getId() >0) {
			sensor = sensorDAO.findById(sensor.getId()).orElse(sensor);
			if (sensor == null) {
				return "redirect:/listar_sensores";
			}
		}else {
			return "redirect:/listar_sensore";
			
		}
		modelo.addAttribute("accion", "consultar");
		modelo.addAttribute("titulo", "Consulta del sensor # " + sensor.getId());
		modelo.addAttribute("sensor", sensor);
		return "sensor/consultar_sensor";
		
		
	}


	@PostMapping("/modificarSensor")
	public String modificarSensor(@Valid @ModelAttribute("sensor") Sensor sensor, BindingResult errores,			
			Model modelo, SessionStatus status) {

		if (sensor.getId() >0) {
			sensor = sensorDAO.findById(sensor.getId()).orElse(sensor);
			if (sensor == null) {
				return "redirect:/listar_sesores";
			}
		}else {
			return "redirect:/listar_sensores";
			
		}
		modelo.addAttribute("sensores",sensorDAO.findAll());
		
		modelo.addAttribute("sensor", sensor);
		modelo.addAttribute("accion", "modificar");
		modelo.addAttribute("titulo", "Modificar sensor # " + sensor.getId());
		
		return "sensor/registrar_sensor";		
		
	}
	
	@PostMapping("/inactivarSensor")
	public String inactivarSensor(@ModelAttribute("sensor") Sensor sensor, Model modelo, RedirectAttributes flash) {
	
		
		if (sensor.getId() > 0) {
			sensor = sensorDAO.findById(sensor.getId()).orElse(sensor);
			sensor.setEstado("0");
			flash.addFlashAttribute("info", "sensor inactivado con exito");

			sensorDAO.save(sensor);
		
		}

		return "redirect:/listarSensores";
		
	}
	
	
	@PostMapping("/activarSensor")
	public String activarSensor(@ModelAttribute("sensor") Sensor sensor, Model modelo, RedirectAttributes flash) {
	
		
		if (sensor.getId() > 0) {
			sensor = sensorDAO.findById(sensor.getId()).orElse(sensor);
			sensor.setEstado("1");
			flash.addFlashAttribute("info", "sensor activado con exito");

			sensorDAO.save(sensor);
		
		}

		return "redirect:/listarSensores";
		
	}
}
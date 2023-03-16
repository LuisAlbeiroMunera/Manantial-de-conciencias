package org.oasisDeVida.model.service;

import java.util.List;

import org.oasisDeVida.modelo.entity.Departamento;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoService {
	public List<Departamento> listarDepartamentos();
	
	public Departamento consultarDepartamento(Long id);
	
	public void agregarDepartamento(Departamento departamento);
	
	public void inactivarDepartamento(Long id);
	
	public Departamento consultarPorIdentificacion(Departamento departamento);

}

package org.avismart.model.service;


import java.util.List;

import org.avismart.modelo.dao.IDepartamentoDao;

import org.avismart.modelo.entity.Departamento;

import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	private IDepartamentoDao departamentoDAO;
	


	@Override
	public Departamento consultarDepartamento(Long id) {
	
		return departamentoDAO.getOne(id);
	}

	@Override
	public void agregarDepartamento(Departamento departamento) {
		departamentoDAO.save(departamento);
		
	}

	@Override
	public void inactivarDepartamento(Long id) {
		departamentoDAO.deleteById(id);
		
	}

	@Override
	public Departamento consultarPorIdentificacion(Departamento departamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> listarDepartamentos() {
		// TODO Auto-generated method stub
		return departamentoDAO.findAll();
	}



	


}

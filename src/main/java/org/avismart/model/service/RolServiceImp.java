package org.avismart.model.service;

import java.util.List;

import org.avismart.modelo.dao.RolRepository;
import org.avismart.modelo.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImp implements RolService{
	@Autowired
	private RolRepository rolRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Rol> listarRoles() {
	
		return rolRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rol consultarRol(Long id) {
		
		return null;
	}

	@Override
	@Transactional
	public void agregarRol(Rol rol) {
		rolRepository.save(rol);
		
	}

	@Override
	public void inactivarRol(Long id) {
		// TODO Auto-generated method stub
		
	}
	

}

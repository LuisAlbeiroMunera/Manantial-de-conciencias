package org.avismart.model.service;

import java.util.ArrayList;
import java.util.List;

import org.avismart.modelo.dao.ICiudadDao;
import org.avismart.modelo.dao.IDepartamentoDao;
import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.entity.Ciudad;
import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CiudadServiceImpl implements CiudadService{

	private ICiudadDao iCiudadDao;
	@Override
	public List<Ciudad> listarCiudades() {
		// TODO Auto-generated method stub
		return iCiudadDao.findAll();
	}

	@Override
	public Ciudad consultarCiudad(Long id) {
		// TODO Auto-generated method stub
		return iCiudadDao.getOne(id);
	}

	@Override
	public void agregarCiudad(Ciudad ciudad) {
		iCiudadDao.save(ciudad);
	}

	@Override
	public void inactivarCiudad(Long id) {
		iCiudadDao.deleteById(id);
		
	}

	@Override
	public Departamento consultarPorIdentificacion(Ciudad ciudad) {
		// TODO Auto-generated method stub
		return null;
	}

	



	


}

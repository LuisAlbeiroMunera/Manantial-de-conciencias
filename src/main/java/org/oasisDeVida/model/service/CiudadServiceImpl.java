package org.oasisDeVida.model.service;

import java.util.ArrayList;
import java.util.List;

import org.oasisDeVida.modelo.dao.ICiudadDao;
import org.oasisDeVida.modelo.dao.IDepartamentoDao;
import org.oasisDeVida.modelo.dao.LoteDAO;
import org.oasisDeVida.modelo.entity.Ciudad;
import org.oasisDeVida.modelo.entity.Departamento;
import org.oasisDeVida.modelo.entity.Lote;
import org.oasisDeVida.modelo.entity.Usuario;
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

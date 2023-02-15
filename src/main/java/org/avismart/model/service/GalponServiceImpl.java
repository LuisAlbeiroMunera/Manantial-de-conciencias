package org.avismart.model.service;

import java.util.ArrayList;
import java.util.List;

import org.avismart.modelo.dao.IDepartamentoDao;
import org.avismart.modelo.dao.IGalponDao;
import org.avismart.modelo.dao.IGranjaDao;
import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.entity.Departamento;
import org.avismart.modelo.entity.Galpon;
import org.avismart.modelo.entity.Granja;
import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GalponServiceImpl implements GalponService{

		 private IGalponDao galponDao;
	@Override
	public List<Galpon> listarGalpones() {
		// TODO Auto-generated method stub
		return galponDao.findAll();
	}

	@Override
	public Galpon consultarGalpon(Long id) {
		// TODO Auto-generated method stub
		return galponDao.getOne(id);
	}

	@Override
	public void agregarGalpon(Galpon galpon) {
		// TODO Auto-generated method stub
		galponDao.save(galpon);
	}

	@Override
	public void inactivarGalpon(Long id) {
		// TODO Auto-generated method stub
		galponDao.findById(id);
	}

	@Override
	public Granja consultarPorIdentificacion(Galpon galpon) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}

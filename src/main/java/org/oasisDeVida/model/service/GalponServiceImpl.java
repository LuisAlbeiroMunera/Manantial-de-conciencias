package org.oasisDeVida.model.service;

import java.util.ArrayList;
import java.util.List;

import org.oasisDeVida.modelo.dao.IDepartamentoDao;
import org.oasisDeVida.modelo.dao.IGalponDao;
import org.oasisDeVida.modelo.dao.IGranjaDao;
import org.oasisDeVida.modelo.dao.LoteDAO;
import org.oasisDeVida.modelo.entity.Departamento;
import org.oasisDeVida.modelo.entity.Galpon;
import org.oasisDeVida.modelo.entity.Granja;
import org.oasisDeVida.modelo.entity.Lote;
import org.oasisDeVida.modelo.entity.Usuario;
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

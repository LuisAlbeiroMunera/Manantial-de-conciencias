package org.oasisDeVida.model.service;

import java.util.ArrayList;
import java.util.List;

import org.oasisDeVida.modelo.dao.LevanteDAO;
import org.oasisDeVida.modelo.dao.LoteDAO;
import org.oasisDeVida.modelo.entity.Levante;
import org.oasisDeVida.modelo.entity.Lote;
import org.oasisDeVida.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class LevanteServiceImp implements LevanteService{
	@Autowired
	private LevanteDAO levanteDao;

	@Override
	public List<Levante> listarLevantes() {
		// TODO Auto-generated method stub
		return levanteDao.findAll();
	}

	@Override
	public Levante consultarLevante(Long id) {
		// TODO Auto-generated method stub
		return levanteDao.getOne(id);
	}

	@Override
	public void agregarLevante(Levante levante) {
		// TODO Auto-generated method stub
		levanteDao.save(levante);
	}

	@Override
	public void inactivarLevante(Long id) {
	levanteDao.deleteById(id);
	}

	@Override
	public Levante consultarPorIdentificacion(Levante levante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Levante> consultarLevantes(Levante levante) {
		// TODO Auto-generated method stub
		return null;
	}






}

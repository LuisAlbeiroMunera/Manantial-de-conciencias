package org.avismart.model.service;

import java.util.ArrayList;
import java.util.List;

import org.avismart.modelo.dao.LoteDAO;
import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class LoteServiceImp implements LoteService{
	@Autowired
	private LoteDAO loteDao;

	@Override
	public List<Lote> listarLotes() {
		// TODO Auto-generated method stub
		return loteDao.findAll();
	}

	@Override
	public Lote consultarLote(Long id) {
		// TODO Auto-generated method stub
		return loteDao.getOne(id);
	}

	@Override
	public void agregarLote(Lote lote) {
		// TODO Auto-generated method stub
		loteDao.save(lote);
	}

	@Override
	public void inactivarLote(Long id) {
		// TODO Auto-generated method stub
		loteDao.deleteById(id);
	}

	@Override
	public Lote consultarPorIdentificacion(Lote lote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lote> consultarLotes(Lote lote) {
		// TODO Auto-generated method stub
		return new ArrayList<Lote>();
	}
	


}

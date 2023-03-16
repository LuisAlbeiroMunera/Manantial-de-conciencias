package org.oasisDeVida.model.service;


import java.util.List;

import org.oasisDeVida.modelo.dao.IGranjaDao;
import org.oasisDeVida.modelo.entity.Granja;
import org.springframework.stereotype.Service;
@Service
public class GranjaServiceImpl implements GranjaService{

	private IGranjaDao granjaDao;

	@Override
	public List<Granja> listarGranjas() {
		// TODO Auto-generated method stub
		return granjaDao.findAll();
	}

	@Override
	public Granja consultarGranja(Long id) {
		// TODO Auto-generated method stub
		return granjaDao.getOne(id);
	}

	@Override
	public void agregarGranja(Granja granja) {
		granjaDao.save(granja);
		
	}

	@Override
	public void inactivarGranja(Long id) {
		granjaDao.deleteById(id);
		
	}

	@Override
	public Granja consultarPorIdentificacion(Granja granja) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}

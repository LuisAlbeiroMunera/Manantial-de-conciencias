package org.avismart.model.service;

import java.util.List;

import org.avismart.modelo.entity.Lote;

import org.springframework.stereotype.Repository;
@Repository
public interface LoteService {
	public List<Lote> listarLotes();
	
	public Lote consultarLote(Long id);
	
	public void agregarLote(Lote lote);
	
	public void inactivarLote(Long id);
	
	public Lote consultarPorIdentificacion(Lote lote);
	public List<Lote>  consultarLotes(Lote lote);
}

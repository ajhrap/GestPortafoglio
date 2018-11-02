package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.TitoloDao;
import com.websystique.springmvc.model.Titolo;


@Service("titoloService")
@Transactional
public class TitoloServiceImpl implements TitoloService {

	@Autowired
	private TitoloDao dao;
	
	public Titolo findById(int id) {
		return dao.findById(id);
	}

	public void saveTitolo(Titolo titolo) {
		dao.saveTitolo(titolo);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTitolo(Titolo titolo) {
		Titolo entity = dao.findById(titolo.getTitoloId());
		if (entity!=null) {
			entity.setIsin(titolo.getIsin());
			entity.setDenominazione(titolo.getDenominazione());
			entity.setMercato(titolo.getMercato());
			entity.setValuta(titolo.getValuta());
		}
	}

	public void deleteTitoloByIsin(String isin) {
		dao.deleteTitoloByIsin(isin);
	}
	
	public List<Titolo> findAllTitoli() {
		return dao.findAllTitoli();
	}

	public Titolo findTitoloByIsin(String isin) {
		return dao.findTitoloByIsin(isin);
	}

	public boolean isTitoloIsinUnique(Integer id, String isin) {
		Titolo titolo = findTitoloByIsin(isin);
		return ( titolo == null || ((id != null) && (titolo.getTitoloId() == id)));
	}
	
}

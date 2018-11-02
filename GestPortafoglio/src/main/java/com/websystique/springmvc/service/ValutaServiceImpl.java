package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.ValutaDao;
import com.websystique.springmvc.model.Valuta;


@Service("valutaService")
@Transactional
public class ValutaServiceImpl implements ValutaService {

	@Autowired
	private ValutaDao dao;
	
	public Valuta findById(int id) {
		return dao.findById(id);
	}

	public void saveValuta(Valuta valuta) {
		dao.saveValuta(valuta);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateValuta(Valuta valuta) {
		Valuta entity = dao.findById(valuta.getValutaId());
		if (entity!=null) {
			entity.setCodice(valuta.getCodice());
			entity.setNome(valuta.getNome());
		}
	}

	public void deleteValutaByCodice(String codice) {
		dao.deleteValutaByCodice(codice);
	}
	
	public List<Valuta> findAllValute() {
		return dao.findAllValute();
	}

	public Valuta findValutaByCodice(String codice) {
		return dao.findValutaByCodice(codice);
	}

	public boolean isValutaCodiceUnique(Integer id, String codice) {
		Valuta valuta = findValutaByCodice(codice);
		return ( valuta == null || ((id != null) && (valuta.getValutaId() == id)));
	}
}

package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.MercatoDao;
import com.websystique.springmvc.model.Mercato;



@Service("mercatoService")
@Transactional
public class MercatoServiceImpl implements MercatoService {

	@Autowired
	private MercatoDao dao;
	
	public Mercato findById(int id) {
		return dao.findById(id);
	}

	public void saveMercato(Mercato mercato) {
		dao.saveMercato(mercato);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateMercato(Mercato mercato) {
		Mercato entity = dao.findById(mercato.getMercatoId());
		if (entity!=null) {
			entity.setCodice(mercato.getCodice());
			entity.setNome(mercato.getNome());
		}
	}

	public void deleteMercatoByCodice(String codice) {
		dao.deleteMercatoByCodice(codice);
	}
	
	public List<Mercato> findAllMercati() {
		return dao.findAllMercati();
	}

	public Mercato findMercatoByCodice(String codice) {
		return dao.findMercatoByCodice(codice);
	}

	public boolean isMercatoCodiceUnique(Integer id, String codice) {
		Mercato mercato = findMercatoByCodice(codice);
		return ( mercato == null || ((id != null) && (mercato.getMercatoId() == id)));
	}
}

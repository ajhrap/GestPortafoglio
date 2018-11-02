package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.PortafoglioDao;
import com.websystique.springmvc.model.Portafoglio;

@Service("portafoglioService")
@Transactional
public class PortafoglioServiceImpl implements PortafoglioService {

	@Autowired
	private PortafoglioDao dao;
	
	public Portafoglio findById(int id) {
		return dao.findById(id);
	}

	public void savePortafoglio(Portafoglio portafoglio) {
		dao.savePortafoglio(portafoglio);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updatePortafoglio(Portafoglio portafoglio) {
		Portafoglio entity = dao.findById(portafoglio.getPortafoglioId());
		if (entity!=null) {
			entity.setTitolo(portafoglio.getTitolo());
			entity.setQuantita(portafoglio.getQuantita());
			entity.setDataAcquisto(portafoglio.getDataAcquisto());
			entity.setPrezzoAcquisto(portafoglio.getPrezzoAcquisto());
			entity.setTassoValutaAcquisto(portafoglio.getTassoValutaAcquisto());
			entity.setDataVendita(portafoglio.getDataVendita());
			entity.setPrezzoVendita(portafoglio.getPrezzoVendita());
			entity.setTassoValutaVendita(portafoglio.getTassoValutaVendita());
			entity.setControvaloreEuro(portafoglio.getControvaloreEuro());
		}
	}

	public void deletePortafoglioById(Integer portafoglioId) {
		dao.deletePortafoglioById(portafoglioId);
	}
	
	public List<Portafoglio> findAllPortafogli() {
		return dao.findAllPortafogli();
	}

	public Portafoglio findPortafoglioByTitolo(Integer idTitolo) {
		return dao.findPortafoglioByTitolo(idTitolo);
	}

	public boolean isPortafoglioTitoloUnique(Integer id, Integer idTitolo) {
		Portafoglio portafoglio = findPortafoglioByTitolo(idTitolo);
		return ( portafoglio == null || ((id != null) && (portafoglio.getPortafoglioId() == id)));
	}
}

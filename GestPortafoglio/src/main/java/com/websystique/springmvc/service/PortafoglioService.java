package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Portafoglio;


public interface PortafoglioService {

	Portafoglio findById(int id);
	
	void savePortafoglio(Portafoglio portafoglio);
	
	void updatePortafoglio(Portafoglio portafoglio);
	
	void deletePortafoglioById(Integer portafoglioId);

	List<Portafoglio> findAllPortafogli(); 
	
	Portafoglio findPortafoglioByTitolo(Integer titoloId);

	boolean isPortafoglioTitoloUnique(Integer portafoglioId, Integer titoloId);
}

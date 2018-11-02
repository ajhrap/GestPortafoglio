package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Portafoglio;


public interface PortafoglioDao {

	Portafoglio findById(int portafoglioId);

	void savePortafoglio(Portafoglio portafoglio);
	
	void deletePortafoglioById(Integer portafoglioId);
	
	List<Portafoglio> findAllPortafogli();

	Portafoglio findPortafoglioByTitolo(Integer titoloId);
}

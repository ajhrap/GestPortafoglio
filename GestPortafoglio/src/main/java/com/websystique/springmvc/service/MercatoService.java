package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Mercato;


public interface MercatoService {

	Mercato findById(int id);
	
	void saveMercato(Mercato mercato);
	
	void updateMercato(Mercato mercato);
	
	void deleteMercatoByCodice(String codice);

	List<Mercato> findAllMercati(); 
	
	Mercato findMercatoByCodice(String codice);

	boolean isMercatoCodiceUnique(Integer id, String codice);
	
}

package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Mercato;

public interface MercatoDao {

	Mercato findById(int id);

	void saveMercato(Mercato mercato);
	
	void deleteMercatoByCodice(String codice);
	
	List<Mercato> findAllMercati();

	Mercato findMercatoByCodice(String codice);
}

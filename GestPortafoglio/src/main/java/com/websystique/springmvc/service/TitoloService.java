package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Titolo;


public interface TitoloService {

	Titolo findById(int id);
	
	void saveTitolo(Titolo titolo);
	
	void updateTitolo(Titolo titolo);
	
	void deleteTitoloByIsin(String isin);

	List<Titolo> findAllTitoli(); 
	
	Titolo findTitoloByIsin(String isin);

	boolean isTitoloIsinUnique(Integer id, String isin);
	
}

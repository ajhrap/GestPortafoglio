package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Titolo;

public interface TitoloDao {

	Titolo findById(int id);

	void saveTitolo(Titolo titolo);
	
	void deleteTitoloByIsin(String isin);
	
	List<Titolo> findAllTitoli();

	Titolo findTitoloByIsin(String isin);

}

package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Valuta;

public interface ValutaDao {

	Valuta findById(int id);

	void saveValuta(Valuta valuta);
	
	void deleteValutaByCodice(String codice);
	
	List<Valuta> findAllValute();

	Valuta findValutaByCodice(String codice);
}

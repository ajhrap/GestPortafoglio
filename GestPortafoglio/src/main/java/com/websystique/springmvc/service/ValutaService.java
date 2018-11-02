package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Valuta;


public interface ValutaService {

	Valuta findById(int id);
	
	void saveValuta(Valuta valuta);
	
	void updateValuta(Valuta valuta);
	
	void deleteValutaByCodice(String codice);

	List<Valuta> findAllValute(); 
	
	Valuta findValutaByCodice(String codice);

	boolean isValutaCodiceUnique(Integer id, String codice);
	
}

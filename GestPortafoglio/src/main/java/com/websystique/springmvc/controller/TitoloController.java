package com.websystique.springmvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.websystique.springmvc.model.Mercato;
import com.websystique.springmvc.model.Titolo;
import com.websystique.springmvc.model.Valuta;
import com.websystique.springmvc.service.MercatoService;
import com.websystique.springmvc.service.TitoloService;
import com.websystique.springmvc.service.ValutaService;


@Controller
@EnableWebMvc
@RequestMapping("/titolo")
public class TitoloController {

	@Autowired
	TitoloService titoloService;

	@Autowired
	MercatoService mercatoService;

	@Autowired
	ValutaService valutaService;

	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listTitoli(ModelMap model) {

		List<Titolo> titoli = titoloService.findAllTitoli();
		model.addAttribute("titoli", titoli);
		return "alltitoli";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newTitolo(ModelMap model) {
		Titolo titolo = new Titolo();
		model.addAttribute("titolo", titolo);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveTitolo(@Valid Titolo titolo, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing
		 * custom @Unique annotation and applying it on field [ssn] of Model class
		 * [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		if (!titoloService.isTitoloIsinUnique(titolo.getTitoloId(), titolo.getIsin())) {
			FieldError isinError = new FieldError("titolo", "isin", messageSource.getMessage("non.unique.isin",
					new String[] { titolo.getIsin() }, Locale.getDefault()));
			result.addError(isinError);
			return "registration";
		}

		titoloService.saveTitolo(titolo);

		model.addAttribute("success", "Titolo " + titolo.getDenominazione() + " registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{isin}-titolo" }, method = RequestMethod.GET)
	public String editTitolo(@PathVariable String isin, ModelMap model) {
		Titolo titolo = titoloService.findTitoloByIsin(isin);
		model.addAttribute("titolo", titolo);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{isin}-titolo" }, method = RequestMethod.POST)
	public String updateTitolo(@Valid Titolo titolo, BindingResult result, ModelMap model, @PathVariable String isin) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!titoloService.isTitoloIsinUnique(titolo.getTitoloId(), titolo.getIsin())) {
			FieldError isinError = new FieldError("titolo", "isin", messageSource.getMessage("non.unique.isin",
					new String[] { titolo.getIsin() }, Locale.getDefault()));
			result.addError(isinError);
			return "registration";
		}

		titoloService.updateTitolo(titolo);

		model.addAttribute("success", "Titolo " + titolo.getDenominazione() + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{isin}-titolo" }, method = RequestMethod.GET)
	public String deleteTitolo(@PathVariable String isin) {
		titoloService.deleteTitoloByIsin(isin);
		return "redirect:/titolo/list";
	}

	@ModelAttribute("mercatiList")
	public Map<Integer, String> getMercatiList() {
		Map<Integer, String> mercatiList = new HashMap<Integer, String>();
		List<Mercato> listaMercati = mercatoService.findAllMercati();
		for (Mercato mercato : listaMercati) {
			mercatiList.put(mercato.getMercatoId(), mercato.getNome());
		}
		return mercatiList;
	}

	@ModelAttribute("valuteList")
	public Map<Integer, String> getValuteList() {
		Map<Integer, String> valuteList = new HashMap<Integer, String>();
		List<Valuta> listaValute = valutaService.findAllValute();
		for (Valuta valuta : listaValute) {
			valuteList.put(valuta.getValutaId(), valuta.getNome());
		}
		return valuteList;
	}
}

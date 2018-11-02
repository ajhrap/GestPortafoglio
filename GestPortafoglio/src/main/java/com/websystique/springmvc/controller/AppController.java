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

import com.websystique.springmvc.model.Portafoglio;
import com.websystique.springmvc.model.Titolo;
import com.websystique.springmvc.service.PortafoglioService;
import com.websystique.springmvc.service.TitoloService;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class AppController {

	@Autowired
	PortafoglioService portafoglioService;
	
	@Autowired
	TitoloService titoloService;
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listTitoli(ModelMap model) {

		List<Portafoglio> portafogli = portafoglioService.findAllPortafogli();
		model.addAttribute("portafogli", portafogli);
		return "allportafogli";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newPortafoglio(ModelMap model) {
		Portafoglio portafoglio = new Portafoglio();
		model.addAttribute("portafoglio", portafoglio);
		model.addAttribute("edit", false);
		return "registrationPort";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveTitolo(@Valid Portafoglio portafoglio, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registrationPort";
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
		if (!portafoglioService.isPortafoglioTitoloUnique(portafoglio.getPortafoglioId(), portafoglio.getTitolo().getTitoloId())) {
			FieldError isinError = new FieldError("titolo", "isin", messageSource.getMessage("non.unique.isin",
					new Integer[] { portafoglio.getTitolo().getTitoloId() }, Locale.getDefault()));
			result.addError(isinError);
			return "registrationPort";
		}

		portafoglioService.savePortafoglio(portafoglio);

		model.addAttribute("success", "Portafoglio " + portafoglio + " registered successfully");
		return "successPort";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{portafoglioId}-portafoglio" }, method = RequestMethod.GET)
	public String editPortafoglio(@PathVariable Integer portafoglioId, ModelMap model) {
		Portafoglio portafoglio = portafoglioService.findById(portafoglioId);
		model.addAttribute("portafoglio", portafoglio);
		model.addAttribute("edit", true);
		return "registrationPort";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{portafoglioId}-portafoglio" }, method = RequestMethod.POST)
	public String updatePortafoglio(@Valid Portafoglio portafoglio, BindingResult result, ModelMap model,
			@PathVariable Integer portafoglioId) {

		if (result.hasErrors()) {
			return "registrationPort";
		}

		if (!portafoglioService.isPortafoglioTitoloUnique(portafoglio.getPortafoglioId(), portafoglio.getTitolo().getTitoloId())) {
			FieldError isinError = new FieldError("portafoglio", "titolo", messageSource.getMessage("non.unique.titolo",
					new Integer[] { portafoglio.getTitolo().getTitoloId() }, Locale.getDefault()));
			result.addError(isinError);
			return "registrationPort";
		}

		portafoglioService.updatePortafoglio(portafoglio);

		model.addAttribute("success", "Portafoglio " + portafoglio.getTitolo().getTitoloId() + " updated successfully");
		return "successPort";
	}

	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{portafoglioId}-portafoglio" }, method = RequestMethod.GET)
	public String deletePortafoglio(@PathVariable Integer portafoglioId) {
		portafoglioService.deletePortafoglioById(portafoglioId);
		return "redirect:/list";
	}

	@ModelAttribute("titoliList")
	public Map<Integer, String> getTitoliList() {
		Map<Integer, String> titoliList = new HashMap<Integer, String>();
		List<Titolo> listaTitoli = titoloService.findAllTitoli();
		for (Titolo titolo : listaTitoli) {
			titoliList.put(titolo.getTitoloId(), titolo.getDenominazione());
		}
		return titoliList;
	}
}

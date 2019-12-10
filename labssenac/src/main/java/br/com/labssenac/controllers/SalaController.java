package br.com.labssenac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Sala;
import br.com.labssenac.repository.SalaRepository;

@Controller
@RequestMapping("/sala")
public class SalaController {

	@Autowired
	private SalaRepository salaRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "sala/formSala";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Sala sala) {

		salaRepository.save(sala);

		return "redirect:/sala/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView listaSalas() {
		ModelAndView modelAndView = new ModelAndView("sala/listaSala");
		Iterable<Sala> salas = salaRepository.findAll();
		modelAndView.addObject("salas", salas);
		return modelAndView;
	}

	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalhesSalas(@PathVariable("id") long id) {
		Sala sala = salaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("sala/detalhesSala");
		modelAndView.addObject("sala", sala);
		return modelAndView;
	}

}

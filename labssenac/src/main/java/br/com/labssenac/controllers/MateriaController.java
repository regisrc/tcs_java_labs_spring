package br.com.labssenac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Materia;
import br.com.labssenac.repository.MateriaRepository;

@Controller
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaRepository materiaRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "materia/formMateria";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Materia materia) {
		materiaRepository.save(materia);

		return "redirect:/materia/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView listaMaterias() {
		ModelAndView modelAndView = new ModelAndView("materia/listaMateria");
		Iterable<Materia> materias = materiaRepository.findAll();
		modelAndView.addObject("materias", materias);
		return modelAndView;
	}

	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalhesMaterias(@PathVariable("id") long id) {
		Materia materia = materiaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("materia/detalhesMateria");
		modelAndView.addObject("materia", materia);
		return modelAndView;
	}

}

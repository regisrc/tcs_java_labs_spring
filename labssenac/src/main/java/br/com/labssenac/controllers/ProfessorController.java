package br.com.labssenac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Professor;
import br.com.labssenac.repository.ProfessorRepository;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "professor/formProfessor";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Professor professor) {
		professorRepository.save(professor);

		return "redirect:/professor/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView listaProfessores() {
		ModelAndView modelAndView = new ModelAndView("professor/listaProfessor");
		Iterable<Professor> professores = professorRepository.findAll();
		modelAndView.addObject("professores", professores);
		return modelAndView;
	}

	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalhesProfessores(@PathVariable("id") long id) {
		Professor professor = professorRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("professor/detalhesProfessor");
		modelAndView.addObject("professor", professor);
		return modelAndView;
	}

}

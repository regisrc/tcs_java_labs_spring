package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

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
		List<Professor> professores = (List<Professor>) professorRepository.findAll();
		List<Professor> professoresAtivos = new ArrayList<Professor>();
		
		for (Professor professor : professores) {
			if(professor.isAtivo())
				professoresAtivos.add(professor);
		}
		
		modelAndView.addObject("professores", professoresAtivos);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Professor dado = professorRepository.findById(id);
		dado.setAtivo(false);
		professorRepository.save(dado);
		return "redirect:/professor/listar";
	}
	
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.POST)
	public String atualizarDado(Professor dadoNovo) {
		Professor dadoVelho = professorRepository.findById(dadoNovo.getId());
		dadoVelho = dadoNovo;
		professorRepository.save(dadoVelho);												
		return "redirect:/professor/listar";
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Professor dado = professorRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("professor/atualizaProfessor");
		modelAndView.addObject("professor", dado);
		return modelAndView;
	}

}

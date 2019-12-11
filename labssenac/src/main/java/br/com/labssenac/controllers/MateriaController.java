package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

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
		List<Materia> materias = (List<Materia>) materiaRepository.findAll();
		List<Materia> materiasAtivos = new ArrayList<Materia>();

		for (Materia materia : materias) {
			if (materia.isAtivo())
				materiasAtivos.add(materia);
		}

		modelAndView.addObject("materias", materiasAtivos);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Materia dado = materiaRepository.findById(id);
		dado.setAtivo(false);
		materiaRepository.save(dado);
		return "redirect:/materia/listar";
	}

	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.POST)
	public String atualizarDado(Materia dadoNovo) {
		Materia dadoVelho = materiaRepository.findById(dadoNovo.getId());
		dadoVelho = dadoNovo;
		materiaRepository.save(dadoVelho);												
		return "redirect:/materia/listar";
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Materia dado = materiaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("materia/atualizaMateria");
		modelAndView.addObject("materia", dado);
		return modelAndView;
	}
}

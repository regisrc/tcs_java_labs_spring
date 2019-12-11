package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

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
		List<Sala> salas = (List<Sala>) salaRepository.findAll();
		List<Sala> salasAtivas = new ArrayList<Sala>();

		for (Sala sala : salas) {
			if (sala.isAtivo())
				salasAtivas.add(sala);
		}

		modelAndView.addObject("salas", salasAtivas);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Sala dado = salaRepository.findById(id);
		dado.setAtivo(false);
		salaRepository.save(dado);
		return "redirect:/sala/listar";
	}

	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.POST)
	public String atualizarDado(Sala dadoNovo) {
		Sala dadoVelho = salaRepository.findById(dadoNovo.getId());
		dadoVelho = dadoNovo;
		salaRepository.save(dadoVelho);
		return "redirect:/sala/listar";
	}

	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Sala dado = salaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("sala/atualizaSala");
		modelAndView.addObject("sala", dado);
		return modelAndView;
	}

}

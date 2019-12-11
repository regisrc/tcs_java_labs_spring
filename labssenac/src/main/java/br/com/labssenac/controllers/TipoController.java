package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Tipo;
import br.com.labssenac.repository.TipoRepository;

@Controller
@RequestMapping("/tipo")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "tipo/formTipo";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Tipo tipo) {
		tipoRepository.save(tipo);

		return "redirect:/tipo/cadastrar";
	}
	
	@RequestMapping("/listar")
	public ModelAndView listaTipos() {
		ModelAndView modelAndView = new ModelAndView("tipo/listaTipo");
		List<Tipo> tipos = (List<Tipo>) tipoRepository.findAll();
		List<Tipo> tiposAtivas = new ArrayList<Tipo>();

		for (Tipo tipo : tipos) {
			if (tipo.isAtivo())
				tiposAtivas.add(tipo);
		}

		modelAndView.addObject("tipos", tiposAtivas);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Tipo dado = tipoRepository.findById(id);
		dado.setAtivo(false);
		tipoRepository.save(dado);
		return "redirect:/tipo/listar";
	}

	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.POST)
	public String atualizarDado(Tipo dadoNovo) {
		Tipo dadoVelho = tipoRepository.findById(dadoNovo.getId());
		dadoVelho = dadoNovo;
		tipoRepository.save(dadoVelho);												
		return "redirect:/tipo/listar";
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Tipo dado = tipoRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("tipo/atualizaTipo");
		modelAndView.addObject("tipo", dado);
		return modelAndView;
	}
	
}

package br.com.labssenac.controllers;

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

		return "redirect:/tipo/formTipo";
	}

	@RequestMapping("/listar")
	public ModelAndView listaTipos() {
		ModelAndView modelAndView = new ModelAndView("tipo/listaTipo");
		Iterable<Tipo> tipos = tipoRepository.findAll();
		modelAndView.addObject("tipos", tipos);
		return modelAndView;
	}

	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalheTipos(@PathVariable("id") long id) {
		Tipo tipo = tipoRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("tipo/detalhesTipo");
		modelAndView.addObject("tipo", tipo);
		return modelAndView;
	}

}

package br.com.labssenac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Usuario;
import br.com.labssenac.repository.ProfessorRepository;
import br.com.labssenac.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuario/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView listaUsuarios() {
		ModelAndView modelAndView = new ModelAndView("usuario/listaUsuario");
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}

	@RequestMapping("/detalhesUsuario/{id}")
	public ModelAndView detalhesUsuarios(@PathVariable("id") long id) {
		Usuario usuario = usuarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("usuario/detalhesUsuario");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}

}

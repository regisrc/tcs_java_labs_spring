package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Usuario;
import br.com.labssenac.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

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
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		List<Usuario> usuariosAtivas = new ArrayList<Usuario>();

		for (Usuario usuario : usuarios) {
			if (usuario.isAtivo())
				usuariosAtivas.add(usuario);
		}

		modelAndView.addObject("usuarios", usuariosAtivas);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Usuario usuario = usuarioRepository.findById(id);
		usuario.setAtivo(false);
		usuarioRepository.save(usuario);
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.POST)
	public String atualizarDado(Usuario usuarioNovo) {
		Usuario usuario = usuarioRepository.findById(usuarioNovo.getId());
		usuario = usuarioNovo;
		usuarioRepository.save(usuario);												
		return "redirect:/usuario/listar";
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Usuario usuario = usuarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("usuario/atualizaUsuario");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
} 

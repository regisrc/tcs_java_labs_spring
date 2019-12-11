package br.com.labssenac.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.labssenac.models.Reserva;
import br.com.labssenac.repository.ReservaRepository;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "reserva/formReserva";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Reserva reserva) {
		reservaRepository.save(reserva);

		return "redirect:/reserva/cadastrar";
	}

	@RequestMapping("/listar")
	public ModelAndView listaReservas() {
		ModelAndView modelAndView = new ModelAndView("reserva/listaReserva");
		List<Reserva> reservas = (List<Reserva>) reservaRepository.findAll();
		List<Reserva> reservasAtivas = new ArrayList<Reserva>();
		
		for (Reserva reserva : reservas) {
			if(reserva.isAtivo())
				reservasAtivas.add(reserva);
		}
		
		modelAndView.addObject("reservas", reservasAtivas);
		return modelAndView;
	}

	@RequestMapping("/deletar/{id}")
	public String deletar(@PathVariable("id") long id) {
		Reserva dado = reservaRepository.findById(id);
		dado.setAtivo(false);
		reservaRepository.save(dado);
		return "redirect:/reserva/listar";
	}

	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.POST)
	public String atualizarDado(Reserva dadoNovo) {
		Reserva dadoVelho = reservaRepository.findById(dadoNovo.getId());
		dadoVelho = dadoNovo;
		reservaRepository.save(dadoVelho);												
		return "redirect:/reserva/listar";
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable("id") long id) {
		Reserva dado = reservaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("reserva/atualizaReserva");
		modelAndView.addObject("reserva", dado);
		return modelAndView;
	}
}

package br.com.labssenac.controllers;

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
		Iterable<Reserva> reservas = reservaRepository.findAll();
		modelAndView.addObject("reservas", reservas);
		return modelAndView;
	}

	@RequestMapping("/detalhes/{id}")
	public ModelAndView detalhesReservas(@PathVariable("id") long id) {
		Reserva reserva = reservaRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("reserva/detalhesReserva");
		modelAndView.addObject("reserva", reserva);
		return modelAndView;
	}

}

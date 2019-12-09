package br.com.labssenac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorController {
	
	@RequestMapping("/cadastrarProfessor")
	public String form(){
		return "professor/formProfessor";
	}
}

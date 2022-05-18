package br.com.vitor.Devflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.LinguagemProgramacao;
import br.com.vitor.Devflix.service.CrudLinguagemProgramcaoService;

@Controller
@RequestMapping("linguagens")
public class LinguagemController {
	
	@Autowired
	private CrudLinguagemProgramcaoService crud;
	
	@GetMapping
	public String liguagens(Model model) {
		List<LinguagemProgramacao> lista = crud.getAll();
		model.addAttribute("linguagens", lista);
		return "user/linguagens";
	}
	
	@GetMapping("{link}")
	public String mostrarLinguagens(@RequestParam(defaultValue = "") String curso, @PathVariable("link") String link, Model model) {
		LinguagemProgramacao linguagem = crud.findByLink(link);
		model.addAttribute("linguagem", linguagem);
		
		List<Curso> lista = crud.getCursosByTitulo(link, curso);
		model.addAttribute("cursos", lista);
		
		return "user/paginaLinguagem";
	}
	
}

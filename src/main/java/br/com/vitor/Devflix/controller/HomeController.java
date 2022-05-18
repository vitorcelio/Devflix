package br.com.vitor.Devflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.service.CrudCursoService;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@Autowired
	private CrudCursoService crud;
	
	@GetMapping
	public String home(Model model) {
		Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
		Page<Curso> lista = crud.getAll(pageable);
		model.addAttribute("cursos", lista);
		return "user/home";
	}
}

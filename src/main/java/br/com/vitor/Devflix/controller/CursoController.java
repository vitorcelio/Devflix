package br.com.vitor.Devflix.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.service.CrudCursoService;

@Controller
@RequestMapping("cursos")
public class CursoController {

	@Autowired
	private CrudCursoService crud;
	
	@GetMapping 
	public String cursos(@RequestParam("page") Optional<Integer> pagina, Model model) {
		int currentPage = pagina.orElse(1);
        
		Pageable page = PageRequest.of(currentPage - 1, 12, Sort.by(Sort.Direction.DESC, "id"));
		Page<Curso> lista = crud.getAllPage(page);
		model.addAttribute("cursos", lista);
		
		int totalPages = lista.getTotalPages(); 
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        model.addAttribute("totalPages", totalPages);
		
		return "user/cursos";
	}
	
	@GetMapping("{link}")
	public String mostrarCursolink(@PathVariable("link") String link, Model model) {
		Curso curso = crud.findLink(link);
		model.addAttribute("curso", curso);
		
		return "user/paginaCurso";
	}
	
	@GetMapping("query")
	public String pesquisarCurso(@RequestParam("q") String q, Model model) {
		List<Curso> lista = crud.findByTituloContaining(q);
		model.addAttribute("cursos", lista);
		model.addAttribute("q", q);
		return "user/querycursos";
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String erroQuery() {
		return "erro404";
	}
	
	
}

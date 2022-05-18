package br.com.vitor.Devflix.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vitor.Devflix.dto.RequisicaoEditarCurso;
import br.com.vitor.Devflix.dto.RequisicaoEditarLinguagem;
import br.com.vitor.Devflix.dto.RequisicaoEditarVideoCurso;
import br.com.vitor.Devflix.dto.RequisicaoNovaLinguagem;
import br.com.vitor.Devflix.dto.RequisicaoNovoCurso;
import br.com.vitor.Devflix.dto.RequisicaoNovoVideoCurso;
import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.LinguagemProgramacao;
import br.com.vitor.Devflix.orm.VideosCurso;
import br.com.vitor.Devflix.service.CrudCursoService;
import br.com.vitor.Devflix.service.CrudLinguagemProgramcaoService;
import br.com.vitor.Devflix.service.CrudVideosCursoService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private CrudCursoService crudCurso;
	
	@Autowired
	private CrudLinguagemProgramcaoService crudLinguagem;
	
	@Autowired
	private CrudVideosCursoService crudVideo;
	
	//====================================================
	// ============== AÇÕES PARA CURSOS ==================
	//====================================================
	@GetMapping("cursos")
	public String listarCursos(Model model, String menu) {
		List<Curso> cursos = crudCurso.findAll();
		model.addAttribute("cursos", cursos);
		
		menu = "curso";
		model.addAttribute("menu", menu);
		
		return "admin/listaCursos";
	}
	
	@GetMapping("adicionar-curso")
	public String adicionarCurso(RequisicaoNovoCurso requisicao, Model model, String menu) {
		List<LinguagemProgramacao> linguagens = crudLinguagem.getAll();
		model.addAttribute("linguagens", linguagens);
		
		menu = "curso";
		model.addAttribute("menu", menu);
		
		return "admin/adicionarCurso";
	}
	
	@PostMapping("novoCurso")
	public String novoCurso(@Valid RequisicaoNovoCurso requisicao, BindingResult result, Model model, String menu) {
		List<LinguagemProgramacao> linguagens = crudLinguagem.getAll();
		model.addAttribute("linguagens", linguagens);
		
		menu = "curso";
		model.addAttribute("menu", menu);
		
		if(result.hasErrors()) {
			return "admin/adicionarCurso";
		}
		
		LinguagemProgramacao linguagem = crudLinguagem.findById(Long.valueOf(requisicao.getLinguagem()));
		Curso curso = requisicao.toCurso();
		curso.setLinguagem(linguagem);
		crudCurso.salvar(curso);
		return "redirect:/admin/cursos";
	}
	
	@GetMapping("cursos/{link}")
	public String editarCurso(RequisicaoEditarCurso requisicao, @PathVariable("link") String link, Model model) {
		List<LinguagemProgramacao> linguagens = crudLinguagem.getAll();
		model.addAttribute("linguagens", linguagens);
		
		Curso curso = crudCurso.findLink(link);
		model.addAttribute("curso", curso);
		return "admin/editarCurso";
	}
	
	@PostMapping("editarCurso")
	public String mudarCurso(@Valid RequisicaoEditarCurso requisicao, BindingResult result, Model model) {
		List<LinguagemProgramacao> linguagens = crudLinguagem.getAll();
		model.addAttribute("linguagens", linguagens);
		
		Curso cursovar = crudCurso.findLink(requisicao.getLink());
		model.addAttribute("curso", cursovar);
		
		if(result.hasErrors()) {
			return "admin/editarCurso";
		}
		
		LinguagemProgramacao linguagem = crudLinguagem.findById(Long.valueOf(requisicao.getLinguagem()));
		Curso curso = requisicao.toCurso();
		curso.setId(Long.valueOf(requisicao.getId()));
		curso.setLinguagem(linguagem);
		crudCurso.salvar(curso);
		return "redirect:/admin/cursos";
	}
	
	@GetMapping("remover-curso/{id}")
	public String removerCurso(@PathVariable("id") Long id) {
		Curso curso = crudCurso.findById(id);
		this.crudCurso.remover(curso);
		return "redirect:/admin/cursos";
	}
	
	//=================================================
	//=========== AÇÕES PARA VÍDEOS ===================
	//=================================================
	
	@GetMapping("/cursos/{link}/videos")
	public String mostrarVideosDoCurso(RequisicaoNovoVideoCurso requisicao, @PathVariable("link") String link, Model model) {
		Curso curso = crudCurso.findLink(link);
		model.addAttribute("curso", curso);
		return "admin/adicionarVideosDoCurso";
	}
	
	@PostMapping("novoVideo")
	public String novoVideo(@Valid RequisicaoNovoVideoCurso requisicao, BindingResult result, Model model) {
		Curso curso = crudCurso.findById(Long.valueOf(requisicao.getIdCurso()));
		model.addAttribute("curso", curso);
		
		if(result.hasErrors()) {
			return "admin/adicionarVideosDoCurso";
		}
		
		VideosCurso video = requisicao.toVideoCurso();
		video.setCurso(curso);
		crudVideo.salvar(video);
		return "redirect:/admin/cursos/"+curso.getLink()+"/videos";
	}
	
	@GetMapping("cursos/{link}/videos/{id}")
	public String editarVideo(RequisicaoEditarVideoCurso requisicao, @PathVariable("link") String link, @PathVariable("id") Long id, Model model) {
		Curso curso = crudCurso.findLink(link);
		model.addAttribute("curso", curso);
		VideosCurso video = crudVideo.findById(id);
		model.addAttribute("video", video);
		return "admin/editarVideosDoCurso";
	}
	
	@PostMapping("editarVideo")
	public String mudarVideo(@Valid RequisicaoEditarVideoCurso requisicao, BindingResult result, Model model) {
		Curso curso = crudCurso.findById(Long.valueOf(requisicao.getIdCurso()));
		
		if(result.hasErrors()) {
			return "admin/editarVideosDoCurso";
		}
		
		
		VideosCurso videovar = crudVideo.findById(Long.valueOf(requisicao.getId()));
		model.addAttribute("video", videovar);
		
		VideosCurso video = requisicao.toVideoCurso();
		video.setCurso(curso);
		video.setId(Long.valueOf(requisicao.getId()));
		crudVideo.salvar(video);
		
		return "redirect:/admin/cursos/"+curso.getLink()+"/videos";
	}
	
	@GetMapping("remover-video/{id}")
	public String removerVideo(@PathVariable("id") Long id) {
		VideosCurso video = crudVideo.findById(id);
		String pagina = video.getCurso().getLink();
		this.crudVideo.remover(video);
		return "redirect:/admin/cursos/"+pagina+"/videos";
	}
	
	//=================================================================
	// ===================== AÇÕES PARA LINGUAGENS ====================
	//=================================================================
	@GetMapping("linguagens")
	public String listarLinguagens(Model model, String menu) {
		List<LinguagemProgramacao> linguagens = crudLinguagem.getAll();
		model.addAttribute("linguagens", linguagens);
		
		menu = "linguagem";
		model.addAttribute("menu", menu);
		
		return "admin/listaLinguagem";
	}
	
	@GetMapping("adicionar-linguagem")
	public String adicionarLinguagem(RequisicaoNovaLinguagem requisicao, Model model, String menu) {
		menu = "linguagem";
		model.addAttribute("menu", menu);
		
		return "admin/adicionarLinguagem";
	}
	
	@PostMapping("novaLinguagem")
	public String novaLinguagem(@Valid RequisicaoNovaLinguagem requisicao, BindingResult result, Model model, String menu) {
		if(result.hasErrors()) {
			return "admin/adicionarLinguagem";
		}
		
		menu = "linguagem";
		model.addAttribute("menu", menu);
		
		LinguagemProgramacao linguagem = requisicao.toLinguagem();
		crudLinguagem.salvar(linguagem);
		return "redirect:/admin/linguagens";
	}
	
	@GetMapping("linguagens/{link}")
	public String editarLinguagem(RequisicaoEditarLinguagem requisicao, @PathVariable("link") String link, Model model) {
		LinguagemProgramacao linguagem = crudLinguagem.findByLink(link);
		model.addAttribute("linguagem", linguagem);
		return "admin/editarLinguagem";
	}
	
	@PostMapping("editarLinguagem")
	public String mudarLinguagem(@Valid RequisicaoEditarLinguagem requisicao, BindingResult result, Model model) {
		LinguagemProgramacao linguagemvar = crudLinguagem.findByLink(requisicao.getLink());
		model.addAttribute("linguagem", linguagemvar);
		
		if(result.hasErrors()) {
			return "admin/editarLinguagem";
		}
		
		
		LinguagemProgramacao linguagem = requisicao.toLinguagem();
		this.crudLinguagem.salvar(linguagem);
		return "redirect:/admin/linguagens";
	}
	
	@GetMapping("remover-linguagem/{id}")
	public String removerLinguagem(@PathVariable("id") Long id) {
		LinguagemProgramacao linguagem = crudLinguagem.findById(id);
		this.crudLinguagem.remover(linguagem);
		return "redirect:/admin/linguagens";
	}
}

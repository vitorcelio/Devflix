/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.VideosCurso;
import br.com.vitor.Devflix.repository.CursoRepository;

/**
 *
 * @author Vitor
 */
@Service
public class CrudCursoService {

     private final CursoRepository repository;

     public CrudCursoService(CursoRepository repository) {
          this.repository = repository;
     }

     public void salvar(Curso curso) {
          repository.save(curso);
     }

     public void remover(Curso curso) {
          repository.delete(curso);
     }

     public Page<Curso> getAll(Pageable pageable) {
          return repository.findAll(pageable);
     }
     
     public Page<Curso> getAllPage(Pageable pageable) {
    	 int pageSize = pageable.getPageSize();
         int currentPage = pageable.getPageNumber();
         int startItem = currentPage * pageSize;
         List<Curso> list;

         if (repository.findAll().size() < startItem) {
             list = Collections.emptyList();
         } else {
             int toIndex = Math.min(startItem + pageSize, repository.findAll().size());
             list = repository.findAll().subList(startItem, toIndex);
         }

         Page<Curso> cursoPage
           = new PageImpl<Curso>(list, PageRequest.of(currentPage, pageSize), repository.findAll().size());

         return cursoPage;
    }
     
     public List<Curso> getCursosByLinguagem(String nome){
          return repository.findByLinguagemNome(nome);
     }
     
     public List<Curso> getCursosByTitulo(String titulo){
          return repository.findByTitulo(titulo);
     }
     
     public List<Curso> getCursosByTituloLike(String titulo){
          return repository.findByTituloLike(titulo);
     }
     
     public List<Curso> findAll(){
    	 return repository.findAll();
     }
     
     public List<VideosCurso> getVideosByIdCurso(Long id){
          return repository.videosDoCurso(id);
     }
     
     public Curso findById(Long id) {
    	 return this.repository.findId(id);
     }
     
     public Curso findLink(String link) {
    	 return this.repository.findByLink(link);
     }
     
     public List<Curso> findContemNoTitulo(Long id, String titulo){
    	 return this.repository.findByIdAndTituloContaining(id, titulo);
     }
     
     public List<Curso> findByTituloContaining(String titulo){
    	 return this.repository.findByTituloContaining(titulo);
     }
}

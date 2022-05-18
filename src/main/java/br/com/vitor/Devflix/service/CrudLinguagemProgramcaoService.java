/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.LinguagemProgramacao;
import br.com.vitor.Devflix.repository.LinguagemProgramacaoRepository;

/**
 *
 * @author Vitor
 */
@Service
public class CrudLinguagemProgramcaoService {

     private final LinguagemProgramacaoRepository repository;

     public CrudLinguagemProgramcaoService(LinguagemProgramacaoRepository repository) {
          this.repository = repository;
     }

     public void salvar(LinguagemProgramacao linguagem) {
          repository.save(linguagem);
     }

     public void remover(LinguagemProgramacao linguagem) {
          repository.delete(linguagem);
     }
     
     public List<LinguagemProgramacao> getAll(){
          return repository.findAll();
     }
     
     public LinguagemProgramacao findById(Long id){
          return repository.findId(id);
     }
     
     public LinguagemProgramacao findByLink(String link) {
    	 return repository.findByLink(link);
     }
     
     public List<Curso> getCursos(){
          return repository.findAllCursos();
     }
     
     public List<Curso> getCursosByLinguagem(Long id){
          return repository.findAllCursosById(id);
     }
     
     public List<Curso> getCursosByTitulo(String link, String titulo){
    	 return this.repository.findByLinkAndCursosTitulo(link, titulo);
     }
}

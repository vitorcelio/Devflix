/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.LinguagemProgramacao;

/**
 *
 * @author Vitor
 */
@Repository
public interface LinguagemProgramacaoRepository extends PagingAndSortingRepository<LinguagemProgramacao, Long> {
     
	 @Cacheable("linguagens")
     List<LinguagemProgramacao> findAll();
     
     @Query("SELECT l from LinguagemProgramacao l where l.id = :id")
     LinguagemProgramacao findId(Long id);
     
     @Query("select l.cursos from LinguagemProgramacao l")
     List<Curso> findAllCursos();

     @Query("select l.cursos from LinguagemProgramacao l where l.id = :id")
     List<Curso> findAllCursosById(Long id);
     
     @Cacheable("linguagem-link")
     LinguagemProgramacao findByLink(String link);
     
     @Query("select c from Curso c where c.linguagem.link = :link and c.titulo like %:titulo%")
     List<Curso> findByLinkAndCursosTitulo(@Param("link") String link, @Param("titulo") String titulo);
     
}

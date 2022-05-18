/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.vitor.Devflix.orm.Curso;
import br.com.vitor.Devflix.orm.VideosCurso;

/**
 *
 * @author Vitor
 */
@Repository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {

     List<Curso> findByLinguagemNome(String nome);

     List<Curso> findByTitulo(String titulo);
     
     @Query("select c from Curso c where c.titulo like '%:titulo%'")
     List<Curso> findByTituloLike(@Param("titulo") String titulo);

     @Query("Select v from VideosCurso v where v.curso = :id")
     List<VideosCurso> videosDoCurso(Long id);
     
     @Cacheable("cursos-home")
     Page<Curso> findAll(Pageable pageable);
     
     @Query("select c from Curso c where c.id = :id")
     Curso findId(Long id);
     
     @Cacheable("curso-link")
     Curso findByLink(String link);
     
     @Cacheable("cursos")
     List<Curso> findAll();
     
     List<Curso> findByIdAndTituloContaining(Long id, String titulo);
     
     List<Curso> findByTituloContaining(String titulo);
}

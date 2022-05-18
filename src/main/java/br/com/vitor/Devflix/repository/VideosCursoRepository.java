/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vitor.Devflix.orm.VideosCurso;

/**
 *
 * @author Vitor
 */
@Repository
public interface VideosCursoRepository extends CrudRepository<VideosCurso, Long>{
     List<VideosCurso> findByCursoTitulo(String titulo);
     List<VideosCurso> findByTitulo(String titulo);
     List<VideosCurso> findByTituloLike(String titulo);
     
     @Query("select v from VideosCurso v where v.id = :id")
     VideosCurso findId(Long id);
}

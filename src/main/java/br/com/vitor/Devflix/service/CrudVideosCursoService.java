/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.vitor.Devflix.orm.VideosCurso;
import br.com.vitor.Devflix.repository.VideosCursoRepository;

/**
 *
 * @author Vitor
 */
@Service
public class CrudVideosCursoService {
     private final VideosCursoRepository repository;
     
     public CrudVideosCursoService(VideosCursoRepository repository){
          this.repository = repository;
     }
     
     public void salvar(VideosCurso videos){
          repository.save(videos);
     }
     
     public void remover(VideosCurso videos){
          repository.delete(videos);
     }
     
     public List<VideosCurso> getAllByCursoTitulo(String titulo){
          return repository.findByCursoTitulo(titulo);
     }
     
     public List<VideosCurso> getAllByTituloLike(String titulo){
          return repository.findByTituloLike(titulo);
     }
     
     public List<VideosCurso> getAllByTitulo(String titulo){
          return repository.findByTitulo(titulo);
     }
     
     public VideosCurso findById(Long id) {
    	 return repository.findId(id);
     }
}

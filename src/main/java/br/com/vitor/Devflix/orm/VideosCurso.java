/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "videos_curso")
public class VideosCurso {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     @Column(nullable = false)
     private String titulo;
     
     @Column(nullable = false)
     private String urlVideo;
     
     @ManyToOne
     private Curso curso;

     public VideosCurso(String titulo, String urlVideo, Curso curso) {
          this.titulo = titulo;
          this.urlVideo = urlVideo;
          this.curso = curso;
     }

     public VideosCurso() {
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getTitulo() {
          return titulo;
     }

     public void setTitulo(String titulo) {
          this.titulo = titulo;
     }

     public String getUrlVideo() {
    	 return urlVideo;
     }

     public void setUrlVideo(String urlVideo) {
          this.urlVideo = urlVideo;
     }

     public Curso getCurso() {
          return curso;
     }

     public void setCurso(Curso curso) {
          this.curso = curso;
     }
     
     
}

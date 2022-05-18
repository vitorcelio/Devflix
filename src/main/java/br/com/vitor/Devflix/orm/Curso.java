/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitor.Devflix.orm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "cursos")
public class Curso {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     @Column(nullable = false)
     private String titulo;
     
     @Column(nullable = false)
     private String descricao;
     
     @ManyToOne(cascade = CascadeType.PERSIST)
     private LinguagemProgramacao linguagem;
     
     @Column(nullable = false)
     private String urlCapaDeFundo;
     
     @Column(nullable = false)
     private String urlVideo;
     
     @Column(nullable = false)
     private String urlPlaylist;
     
     @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
     private List<VideosCurso> videos = new ArrayList<>();
     
     @Column(unique = true)
     private String link;

     public Curso(String titulo, String descricao, LinguagemProgramacao linguagem, String urlCapaDeFundo, String urlVideo, String urlPlaylist) {
          this.titulo = titulo;
          this.descricao = descricao;
          this.linguagem = linguagem;
          this.urlCapaDeFundo = urlCapaDeFundo;
          this.urlVideo = urlVideo;
          this.urlPlaylist = urlPlaylist;
     }

     public Curso() {
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

     public String getDescricao() {
          return descricao;
     }

     public void setDescricao(String descricao) {
          this.descricao = descricao;
     }

     public LinguagemProgramacao getLinguagem() {
          return linguagem;
     }

     public void setLinguagem(LinguagemProgramacao linguagem) {
          this.linguagem = linguagem;
     }

     public String getUrlCapaDeFundo() {
          return urlCapaDeFundo;
     }

     public void setUrlCapaDeFundo(String urlCapaDeFundo) {
          this.urlCapaDeFundo = urlCapaDeFundo;
     }

     public String getUrlVideo() {
    	 return urlVideo;
     }

     public void setUrlVideo(String urlVideo) {
          this.urlVideo = urlVideo;
     }

     public String getUrlPlaylist() {
          return urlPlaylist;
     }

     public void setUrlPlaylist(String urlPlaylist) {
          this.urlPlaylist = urlPlaylist;
     }

     public List<VideosCurso> getVideos() {
          return videos;
     }

     public void setVideos(List<VideosCurso> videos) {
          this.videos = videos;
     }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
     
     

}

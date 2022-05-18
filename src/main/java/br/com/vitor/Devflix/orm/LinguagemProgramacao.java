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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "linguagens_de_programacao")
public class LinguagemProgramacao {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     
     @Column(nullable = false)
     private String nome;
     
     @Column(nullable = false)
     private String imagem;
     
     @OneToMany(mappedBy = "linguagem", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
     private List<Curso> cursos = new ArrayList<>();
     
     @Column(unique = true)
     private String link;

     public LinguagemProgramacao(String nome, String imagem, String link) {
          this.nome = nome;
          this.imagem = imagem;
          this.link = link;
     }

     public LinguagemProgramacao() {
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getNome() {
          return nome;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

     public List<Curso> getCursos() {
          return cursos;
     }

     public void setCursos(List<Curso> cursos) {
          this.cursos = cursos;
     }

     public String getImagem() {
          return imagem;
     }

     public void setImagem(String imagem) {
          this.imagem = imagem;
     }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
     
     
}

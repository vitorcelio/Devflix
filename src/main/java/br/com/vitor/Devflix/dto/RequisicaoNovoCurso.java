package br.com.vitor.Devflix.dto;

import javax.validation.constraints.NotBlank;
import br.com.vitor.Devflix.orm.Curso;

public class RequisicaoNovoCurso {

	@NotBlank
	private String titulo;
	
	@NotBlank
	private String urlVideo;
	
	@NotBlank(message = "selecione alguma linguagem")
	private String linguagem;
	
	@NotBlank
	private String urlCapa;
	
	@NotBlank
	private String linkPlaylist;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String link;

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

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getUrlCapa() {
		return urlCapa;
	}

	public void setUrlCapa(String urlCapa) {
		this.urlCapa = urlCapa;
	}

	public String getLinkPlaylist() {
		return linkPlaylist;
	}

	public void setLinkPlaylist(String linkPlaylist) {
		this.linkPlaylist = linkPlaylist;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Curso toCurso() {
		Curso curso = new Curso();
		curso.setTitulo(titulo);
		
		String[] idVideo = this.urlVideo.split("=");
		String urlVideoCerto = "https://www.youtube-nocookie.com/embed/"+idVideo[1];
		curso.setUrlVideo(urlVideoCerto);
		
		curso.setUrlCapaDeFundo(urlCapa);
		curso.setUrlPlaylist(linkPlaylist);
		curso.setDescricao(descricao);
		curso.setLink(link);
		return curso;
	}

}

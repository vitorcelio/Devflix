package br.com.vitor.Devflix.dto;

import javax.validation.constraints.NotBlank;

import br.com.vitor.Devflix.orm.VideosCurso;

public class RequisicaoEditarVideoCurso {
	
	private String id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String urlVideo;
	
	private String idCurso;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	
	
	public VideosCurso toVideoCurso() {
		VideosCurso video = new VideosCurso();
		video.setTitulo(titulo);
		
		if(this.urlVideo.contains("https://www.youtube.com/watch?")) {
			String[] idVideo = this.urlVideo.split("=");
			this.urlVideo = "https://www.youtube-nocookie.com/embed/"+idVideo[1];			
		}
		
		video.setUrlVideo(urlVideo);
		return video;
	}
	
}

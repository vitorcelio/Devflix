package br.com.vitor.Devflix.dto;

import javax.validation.constraints.NotBlank;

import br.com.vitor.Devflix.orm.LinguagemProgramacao;

public class RequisicaoEditarLinguagem {
	
	private String id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String imagem;
	
	@NotBlank
	private String link;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public LinguagemProgramacao toLinguagem() {
		LinguagemProgramacao linguagem = new LinguagemProgramacao();
		linguagem.setId(Long.parseLong(id));
		linguagem.setNome(nome);
		linguagem.setImagem(imagem);
		linguagem.setLink(link);
		return linguagem;
	}
	
	
}

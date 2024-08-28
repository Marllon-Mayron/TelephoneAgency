package com.msilva.telephoneAgency.domain.dto;

import java.time.LocalDateTime;

import com.msilva.telephoneAgency.domain.entity.Contato;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContatoDTO {
	private Long id;
	private String nome;
	private String email;
	private String celular;
	private String telefone;
	private String snFavorito;
	private String snAtivo;
	private LocalDateTime dhCad;
	

	
	public ContatoDTO(Contato contato) {
		this.id = contato.getId(); 
		this.nome = contato.getNome();
	    this.email = contato.getEmail();
	    this.celular = contato.getCelular();
	    this.telefone = contato.getTelefone();
	    this.snFavorito = contato.getSnFavorito(); 
	    this.snAtivo = contato.getSnAtivo();
	    this.dhCad = contato.getDhCad();
	}
	
	public Contato toEntity() {
		Contato contato = new Contato();
		contato.setId(id);
		contato.setCelular(celular);
		contato.setDhCad(dhCad);
		contato.setEmail(email);
		contato.setNome(nome);
		contato.setSnAtivo(snAtivo);
		contato.setSnFavorito(snFavorito);
		contato.setTelefone(telefone);
		
		return contato;
	}
}

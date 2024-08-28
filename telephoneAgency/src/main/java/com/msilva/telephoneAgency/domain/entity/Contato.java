package com.msilva.telephoneAgency.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="contato", schema = "desafio")
@Getter
@Setter
public class Contato {
	@Id
	@Column(name="contato_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="contato_nome")
	private String nome;
	@Column(name="contato_email")
	private String email;
	@Column(name="contato_celular")
	private String celular;
	@Column(name="contato_telefone")
	private String telefone;
	@Column(name="contato_sn_favorito")
	private String snFavorito;
	@Column(name="contato_sn_ativo")
	private String snAtivo;
	@Column(name="contato_dh_cad")
	private LocalDateTime dhCad;
	
}

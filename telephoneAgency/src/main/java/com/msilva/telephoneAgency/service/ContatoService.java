package com.msilva.telephoneAgency.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.msilva.telephoneAgency.domain.dto.ContatoDTO;
import com.msilva.telephoneAgency.domain.entity.Contato;
import com.msilva.telephoneAgency.exception.ContatoJaExistenteException;

@Repository
public interface ContatoService{
	ContatoDTO findById(Long id);

	ContatoDTO findByCelular(String contato_celular);
	
	void cadastrar(ContatoDTO contato) throws ContatoJaExistenteException;
	
	void atualizar(ContatoDTO contato) throws Exception;
	
	List<ContatoDTO> buscarPorFiltro(boolean snFavorito, boolean snAtivo);
	
	List<ContatoDTO> buscaContatosFavoritos();
}
